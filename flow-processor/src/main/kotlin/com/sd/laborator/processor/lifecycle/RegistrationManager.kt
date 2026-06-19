package com.sd.laborator.processor.lifecycle

import com.sd.laborator.processor.config.ProcessorProperties
import com.sd.laborator.processor.registry.IRegistryClient
import com.sd.laborator.processor.state.ProcessorState
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import javax.annotation.PreDestroy

/**
 * Gestioneaza CICLUL DE VIATA al procesorului fata de registru.
 *
 * Acesta este exact "mecanismul de tip registru" cerut in enunt:
 *   - la PORNIRE (ApplicationReadyEvent, dupa ce serverul HTTP e gata) -> se inregistreaza
 *   - la OPRIRE/DISTRUGERE (@PreDestroy, la inchiderea contextului Spring) -> se sterge
 *
 * Responsabilitate unica (SRP): doar inscrierea/retragerea din registru.
 */
@Component
class RegistrationManager(
    private val registryClient: IRegistryClient,
    private val state: ProcessorState,
    private val properties: ProcessorProperties
) {

    @EventListener(ApplicationReadyEvent::class)
    fun onStartup() {
        try {
            val self = registryClient.register()
            state.self = self
            println("[LIFECYCLE] '${properties.name}' s-a INREGISTRAT in registru cu id=${self.id}")
        } catch (e: Exception) {
            println("[LIFECYCLE] Inregistrarea in registru a esuat: ${e.message}")
        }
    }

    @PreDestroy
    fun onShutdown() {
        val self = state.self ?: return
        try {
            registryClient.deregister(self.id)
            println("[LIFECYCLE] '${properties.name}' s-a STERS din registru (id=${self.id})")
        } catch (e: Exception) {
            println("[LIFECYCLE] Stergerea din registru a esuat: ${e.message}")
        }
    }
}
