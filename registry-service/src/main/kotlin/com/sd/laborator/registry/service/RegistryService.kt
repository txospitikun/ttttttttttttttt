package com.sd.laborator.registry.service

import com.sd.laborator.registry.dto.RegistrationRequest
import com.sd.laborator.registry.model.FlowProcessorInstance
import com.sd.laborator.registry.repository.IFlowProcessorRepository
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.UUID

/**
 * Logica de business a registrului: genereaza id-ul unic, marcheaza momentul
 * inregistrarii si deleaga persistenta catre repository.
 *
 * Responsabilitate unica (SRP): regulile de inregistrare/stergere.
 * Depinde de abstractizarea repository-ului (DIP).
 */
@Service
class RegistryService(
    private val repository: IFlowProcessorRepository
) : IRegistryService {

    override fun register(request: RegistrationRequest): FlowProcessorInstance {
        val instance = FlowProcessorInstance(
            id = UUID.randomUUID().toString(),
            name = request.name,
            host = request.host,
            port = request.port,
            type = request.type,
            registeredAt = Instant.now()
        )
        repository.save(instance)
        println("[REGISTRY] Inregistrat procesor de flux: ${instance.name} (${instance.host}:${instance.port}) id=${instance.id}")
        return instance
    }

    override fun deregister(id: String): Boolean {
        val removed = repository.deleteById(id)
        if (removed) {
            println("[REGISTRY] Sters procesorul de flux cu id=$id")
        }
        return removed
    }

    override fun listAll(): List<FlowProcessorInstance> = repository.findAll()

    override fun listByType(type: String): List<FlowProcessorInstance> = repository.findByType(type)

    override fun get(id: String): FlowProcessorInstance? = repository.findById(id)
}
