package com.sd.laborator.processor.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

/**
 * Configuratia acestui procesor de flux, citita din application.yml / variabile de mediu.
 *  - name        : numele afisat in chat si in registru (ex: alice)
 *  - host        : hostname-ul la care il pot contacta ceilalti (localhost local, numele containerului in Docker)
 *  - port        : portul HTTP propriu (= server.port)
 *  - registryUrl : adresa registrului
 */
@Component
class ProcessorProperties(
    @Value("\${processor.name}") val name: String,
    @Value("\${processor.host}") val host: String,
    @Value("\${server.port}") val port: Int,
    @Value("\${registry.url}") val registryUrl: String
)
