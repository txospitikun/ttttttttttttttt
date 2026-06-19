package com.sd.laborator.processor.service

import com.sd.laborator.processor.config.ProcessorProperties
import com.sd.laborator.processor.model.ChatMessage
import com.sd.laborator.processor.registry.IRegistryClient
import com.sd.laborator.processor.state.ProcessorState
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.time.Instant
import java.util.Collections

/**
 * Logica de chat bazata pe COREGRAFIE:
 *  - la difuzare, intreaba registrul cine sunt ceilalti participanti (descoperire),
 *    apoi le trimite mesajul DIRECT, peer-to-peer (fara broker / orchestrator central);
 *  - la receptie, doar memoreaza mesajul.
 *
 * Responsabilitate unica (SRP): regulile de chat. Descoperirea este delegata
 * clientului de registru (DIP), iar transportul HTTP catre peers e facut cu RestTemplate.
 */
@Service
class ChatService(
    private val restTemplate: RestTemplate,
    private val registryClient: IRegistryClient,
    private val state: ProcessorState,
    private val properties: ProcessorProperties
) : IChatService {

    // lista thread-safe (mesajele pot sosi concurent de la mai multi peers)
    private val received = Collections.synchronizedList(mutableListOf<ChatMessage>())

    override fun broadcast(text: String): Int {
        val myId = state.self?.id
        // descoperire prin registru, excluzandu-ne pe noi insine
        val peers = registryClient.fetchPeers().filter { it.id != myId }

        val message = ChatMessage(
            from = properties.name,
            text = text,
            timestamp = Instant.now().toString()
        )

        var delivered = 0
        peers.forEach { peer ->
            try {
                restTemplate.postForObject(
                    "http://${peer.host}:${peer.port}/chat/receive",
                    message,
                    Void::class.java
                )
                delivered++
                println("[CHAT] Trimis catre ${peer.name} (${peer.host}:${peer.port}): \"$text\"")
            } catch (e: Exception) {
                // intr-un sistem distribuit un peer poate fi indisponibil: continuam cu ceilalti
                println("[CHAT] Nu am putut livra catre ${peer.name} (${peer.host}:${peer.port}): ${e.message}")
            }
        }
        return delivered
    }

    override fun receive(message: ChatMessage) {
        received.add(message)
        println("[CHAT] Mesaj primit de la ${message.from}: \"${message.text}\"")
    }

    override fun inbox(): List<ChatMessage> = synchronized(received) { received.toList() }
}
