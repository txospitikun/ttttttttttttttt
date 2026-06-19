package com.sd.laborator.registry.repository

import com.sd.laborator.registry.model.FlowProcessorInstance

/**
 * Contractul stratului de persistenta (DIP + ISP).
 * Serviciul depinde de aceasta abstractizare, nu de implementarea concreta.
 */
interface IFlowProcessorRepository {
    fun save(instance: FlowProcessorInstance)
    fun deleteById(id: String): Boolean
    fun findAll(): List<FlowProcessorInstance>
    fun findById(id: String): FlowProcessorInstance?
    fun findByType(type: String): List<FlowProcessorInstance>
}
