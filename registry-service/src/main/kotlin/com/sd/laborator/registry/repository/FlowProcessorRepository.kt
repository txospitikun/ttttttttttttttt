package com.sd.laborator.registry.repository

import com.sd.laborator.registry.model.FlowProcessorInstance
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import java.sql.Timestamp

/**
 * Implementarea stratului de persistenta peste baza de date H2,
 * folosind JdbcTemplate + RowMapper (acelasi tipar ca in Lab 6 - BeerApp).
 *
 * Responsabilitate unica (SRP): doar accesul la date (CRUD), nimic altceva.
 */
@Repository
class FlowProcessorRepository(
    private val jdbcTemplate: JdbcTemplate
) : IFlowProcessorRepository {

    private val rowMapper = RowMapper { rs, _ ->
        FlowProcessorInstance(
            id = rs.getString("id"),
            name = rs.getString("name"),
            host = rs.getString("host"),
            port = rs.getInt("port"),
            type = rs.getString("type"),
            registeredAt = rs.getTimestamp("registered_at").toInstant()
        )
    }

    override fun save(instance: FlowProcessorInstance) {
        jdbcTemplate.update(
            "INSERT INTO flow_processors(id, name, host, port, type, registered_at) VALUES (?, ?, ?, ?, ?, ?)",
            instance.id,
            instance.name,
            instance.host,
            instance.port,
            instance.type,
            Timestamp.from(instance.registeredAt)
        )
    }

    override fun deleteById(id: String): Boolean =
        jdbcTemplate.update("DELETE FROM flow_processors WHERE id = ?", id) > 0

    override fun findAll(): List<FlowProcessorInstance> =
        jdbcTemplate.query("SELECT * FROM flow_processors ORDER BY registered_at", rowMapper)

    override fun findById(id: String): FlowProcessorInstance? =
        jdbcTemplate.query("SELECT * FROM flow_processors WHERE id = ?", rowMapper, id).firstOrNull()

    override fun findByType(type: String): List<FlowProcessorInstance> =
        jdbcTemplate.query("SELECT * FROM flow_processors WHERE type = ? ORDER BY registered_at", rowMapper, type)
}
