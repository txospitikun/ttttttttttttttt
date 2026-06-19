package com.sd.laborator.processor.controller

import com.sd.laborator.processor.dto.SendMessageRequest
import com.sd.laborator.processor.model.ChatMessage
import com.sd.laborator.processor.service.IChatService
import com.sd.laborator.processor.state.ProcessorState
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * API-ul HTTP al procesorului de flux. Singura responsabilitate: maparea HTTP (SRP).
 *
 *  POST /chat/send     -> difuzeaza un mesaj catre toti ceilalti participanti (coregrafie)
 *  POST /chat/receive  -> primeste un mesaj de la alt procesor (apelat peer-to-peer)
 *  GET  /chat/inbox    -> mesajele primite de acest procesor
 *  GET  /chat/whoami   -> identitatea proprie (asa cum a fost inregistrata in registru)
 */
@RestController
@RequestMapping("/chat")
class ChatController(
    private val chatService: IChatService,
    private val state: ProcessorState
) {

    @PostMapping("/send")
    fun send(@RequestBody request: SendMessageRequest): ResponseEntity<Map<String, Any>> {
        val delivered = chatService.broadcast(request.text)
        return ResponseEntity.ok(mapOf("delivered" to delivered, "text" to request.text))
    }

    @PostMapping("/receive")
    fun receive(@RequestBody message: ChatMessage): ResponseEntity<Unit> {
        chatService.receive(message)
        return ResponseEntity(HttpStatus.ACCEPTED)
    }

    @GetMapping("/inbox")
    fun inbox(): List<ChatMessage> = chatService.inbox()

    @GetMapping("/whoami")
    fun whoami(): ResponseEntity<Any> =
        state.self?.let { ResponseEntity.ok<Any>(it) } ?: ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE)
}
