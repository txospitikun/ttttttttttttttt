package com.sd.laborator.registry

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * REGISTRY-SERVICE (mecanismul de tip registru)
 *
 * Microserviciu web cu baza de date (H2) care tine evidenta tuturor
 * PROCESOARELOR DE FLUX active din sistem. Procesoarele de flux se
 * inregistreaza la pornire si se sterg la oprire, comunicand cu acest
 * serviciu exclusiv prin HTTP.
 *
 * Acest serviciu NU coordoneaza fluxul de mesaje (nu este orchestrator);
 * el este un simplu director/registru, ceea ce permite COREGRAFIA intre
 * procesoarele de flux.
 */
@SpringBootApplication
class RegistryApplication

fun main(args: Array<String>) {
    runApplication<RegistryApplication>(*args)
}
