package com.sd.laborator.registry.dto

/**
 * Corpul cererii HTTP de inregistrare trimisa de un procesor de flux.
 * Valorile implicite permit deserializarea JSON fara constructor explicit.
 */
data class RegistrationRequest(
    val name: String = "",
    val host: String = "",
    val port: Int = 0,
    val type: String = "flow-processor"
)
