package com.pessoa.jonathan.livraria.debug.repository

import com.pessoa.jonathan.livraria.debug.entity.RoleEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RoleRepository : JpaRepository<RoleEntity, Long> {
    fun findByName(name: String): Optional<RoleEntity>
}