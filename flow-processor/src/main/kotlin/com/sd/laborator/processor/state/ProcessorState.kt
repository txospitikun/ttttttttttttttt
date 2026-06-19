package com.sd.laborator.processor.state

import com.sd.laborator.processor.model.PeerInfo
import org.springframework.stereotype.Component

/**
 * Starea proprie a procesorului dupa inregistrare (in special `id`-ul primit de
 * la registru). Este folosita pentru a ne exclude pe noi insine din lista de peers
 * atunci cand difuzam mesaje (altfel ne-am trimite mesaje noua insine).
 */
@Component
class ProcessorState {
    @Volatile
    var self: PeerInfo? = null
}
