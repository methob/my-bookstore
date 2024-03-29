package com.pessoa.jonathan.livraria.debug.repository

import com.pessoa.jonathan.livraria.debug.entity.BookEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : JpaRepository<BookEntity, Long> {

}