package com.sd.laborator.registry.controller

import com.sd.laborator.registry.dto.RegistrationRequest
import com.sd.laborator.registry.model.FlowProcessorInstance
import com.sd.laborator.registry.service.IRegistryService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * API-ul HTTP al registrului. Singura responsabilitate: maparea cererilor HTTP
 * pe operatiile serviciului (SRP). Comunicarea cu registrul se face EXCLUSIV prin HTTP.
 *
 *  POST   /processors          -> inregistreaza un procesor de flux
 *  DELETE /processors/{id}     -> sterge un procesor de flux
 *  GET    /processors          -> listeaza toate procesoarele (optional ?type=...)
 *  GET    /processors/{id}     -> detaliile unui procesor
 */
@RestController
@RequestMapping("/processors")
class RegistryController(
    private val registryService: IRegistryService
) {

    @PostMapping
    fun register(@RequestBody request: RegistrationRequest): ResponseEntity<FlowProcessorInstance> =
        ResponseEntity(registryService.register(request), HttpStatus.CREATED)

    @DeleteMapping("/{id}")
    fun deregister(@PathVariable id: String): ResponseEntity<Unit> =
        if (registryService.deregister(id)) ResponseEntity(HttpStatus.NO_CONTENT)
        else ResponseEntity(HttpStatus.NOT_FOUND)

    @GetMapping
    fun list(@RequestParam(required = false) type: String?): List<FlowProcessorInstance> =
        if (type.isNullOrBlank()) registryService.listAll() else registryService.listByType(type)

    @GetMapping("/{id}")
    fun get(@PathVariable id: String): ResponseEntity<FlowProcessorInstance> =
        registryService.get(id)?.let { ResponseEntity.ok(it) } ?: ResponseEntity(HttpStatus.NOT_FOUND)
}
