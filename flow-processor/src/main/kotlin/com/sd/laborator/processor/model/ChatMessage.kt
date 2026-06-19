package com.sd.laborator.processor.model

/**
 * Mesajul de chat schimbat peer-to-peer intre procesoarele de flux.
 */
data class ChatMessage(
    val from: String = "",
    val text: String = "",
    val timestamp: String = ""
)
