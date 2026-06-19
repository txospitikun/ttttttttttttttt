package com.sd.laborator.processor.service

import com.sd.laborator.processor.model.ChatMessage

/**
 * Contractul logicii de chat (DIP + ISP).
 */
interface IChatService {
    /** Difuzeaza un mesaj catre toti ceilalti participanti; intoarce cate au fost contactati. */
    fun broadcast(text: String): Int

    /** Inregistreaza local un mesaj primit. */
    fun receive(message: ChatMessage)

    /** Mesajele primite pana acum. */
    fun inbox(): List<ChatMessage>
}
