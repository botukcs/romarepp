package com.example.theatr.repository

import com.example.theatr.entity.Product
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository

interface ProductRepository: CrudRepository<Product, Long> {
    @Query("SELECT * FROM product WHERE id BETWEEN (SELECT MIN(id) FROM product) and ((SELECT MIN(id) FROM product)+ :firstId)")
    fun getAllProduct(firstId: Int): List<Product>

    fun getProductByName(name: String): Product
}