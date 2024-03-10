package com.pessoa.jonathan.livraria.debug.repository

import com.pessoa.jonathan.livraria.debug.entity.RentalHistoricEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface RentalHistoricRepository : JpaRepository<RentalHistoricEntity, Long> {

    fun findByUserIdAndBookId(userId: Long, bookId: Long): Optional<RentalHistoricEntity>
}