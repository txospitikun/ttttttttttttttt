package com.sd.laborator.processor.dto

/**
 * Corpul cererii prin care i se cere acestui procesor sa transmita
 * un mesaj catre toti ceilalti participanti la chat.
 */
data class SendMessageRequest(
    val text: String = ""
)
