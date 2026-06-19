package com.sd.laborator.registry.service

import com.sd.laborator.registry.dto.RegistrationRequest
import com.sd.laborator.registry.model.FlowProcessorInstance

/**
 * Contractul logicii de business a registrului (DIP).
 * Controller-ul depinde de aceasta interfata, nu de implementare.
 */
interface IRegistryService {
    fun register(request: RegistrationRequest): FlowProcessorInstance
    fun deregister(id: String): Boolean
    fun listAll(): List<FlowProcessorInstance>
    fun listByType(type: String): List<FlowProcessorInstance>
    fun get(id: String): FlowProcessorInstance?
}
