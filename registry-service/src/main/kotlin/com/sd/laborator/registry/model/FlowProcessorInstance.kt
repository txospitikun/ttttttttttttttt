package com.sd.laborator.registry.model

import java.time.Instant

/**
 * Modelul (entitatea) unei instante de procesor de flux inregistrate in registru.
 * Corespunde unei linii din tabela `flow_processors`.
 */
data class FlowProcessorInstance(
    val id: String,
    val name: String,
    val host: String,
    val port: Int,
    val type: String,
    val registeredAt: Instant
)
