package com.sd.laborator.processor

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * FLOW-PROCESSOR (procesorul de flux / nodul de chat)
 *
 * Inlocuieste fostii "subscriberi" (Teacher/Student) din chat-ul Laboratorului 8.
 * Ciclul de viata:
 *   1. la pornire  -> se INREGISTREAZA in registru (HTTP POST /processors)
 *   2. in executie -> descopera ceilalti participanti din registru (HTTP GET /processors)
 *                     si schimba mesaje de chat DIRECT, peer-to-peer (COREGRAFIE)
 *   3. la oprire   -> se STERGE din registru (HTTP DELETE /processors/{id})
 *
 * Se pot porni oricate instante (alice, bob, charlie, ...), fiecare in propria
 * masina virtuala (container Docker).
 */
@SpringBootApplication
class FlowProcessorApplication

fun main(args: Array<String>) {
    runApplication<FlowProcessorApplication>(*args)
}
