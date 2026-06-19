package com.sd.laborator.processor.registry

import com.sd.laborator.processor.config.ProcessorProperties
import com.sd.laborator.processor.dto.RegistrationRequest
import com.sd.laborator.processor.model.PeerInfo
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

/**
 * Implementarea clientului de registru peste HTTP (RestTemplate).
 *
 * Responsabilitate unica (SRP): comunicarea cu microserviciul de registru.
 * Daca maine registrul s-ar schimba (alt protocol), doar aceasta clasa s-ar modifica.
 */
@Component
class RegistryClient(
    private val restTemplate: RestTemplate,
    private val properties: ProcessorProperties
) : IRegistryClient {

    private val processorsEndpoint: String
        get() = "${properties.registryUrl}/processors"

    override fun register(): PeerInfo {
        val request = RegistrationRequest(
            name = properties.name,
            host = properties.host,
            port = properties.port
        )
        return restTemplate.postForObject(processorsEndpoint, request, PeerInfo::class.java)
            ?: throw IllegalStateException("Registrul nu a returnat o instanta valida la inregistrare")
    }

    override fun deregister(id: String) {
        restTemplate.delete("$processorsEndpoint/$id")
    }

    override fun fetchPeers(): List<PeerInfo> {
        val peers = restTemplate.getForObject(processorsEndpoint, Array<PeerInfo>::class.java)
        return peers?.toList() ?: emptyList()
    }
}
