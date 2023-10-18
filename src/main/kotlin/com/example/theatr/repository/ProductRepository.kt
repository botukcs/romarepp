package com.example.theatr.repository

import com.example.theatr.entity.Product
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository

interface ProductRepository: CrudRepository<Product, Long> {
    @Query("SELECT * FROM product")
    fun getAllProduct(): List<Product>
}