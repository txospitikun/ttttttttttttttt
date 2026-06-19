package com.sd.laborator.processor.dto

/**
 * Corpul cererii HTTP de inregistrare trimisa de acest procesor catre registru.
 */
data class RegistrationRequest(
    val name: String,
    val host: String,
    val port: Int,
    val type: String = "flow-processor"
)
