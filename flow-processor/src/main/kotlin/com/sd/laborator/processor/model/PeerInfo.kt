package com.sd.laborator.processor.model

/**
 * Informatiile despre un procesor de flux, asa cum sunt returnate de registru.
 * Folosit atat pentru raspunsul la inregistrare (contine `id`) cat si pentru
 * lista de "peers" descoperita prin coregrafie.
 *
 * Eventualele campuri suplimentare din JSON (ex: registeredAt) sunt ignorate.
 */
data class PeerInfo(
    val id: String = "",
    val name: String = "",
    val host: String = "",
    val port: Int = 0,
    val type: String = "flow-processor"
)
