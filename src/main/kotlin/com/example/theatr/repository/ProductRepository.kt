package com.example.theatr.repository

import com.example.theatr.entity.Product
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository

interface ProductRepository: CrudRepository<Product, Long> {
    @Query("SELECT * FROM (SELECT * FROM product) a LIMIT :firstId")
    fun getAllProduct(firstId: Int, fieldName: String, sortType: String): List<Product>

    @Query("SELECT * FROM product WHERE name LIKE concat('%', replace(replace(:name, '%', '\\\\%'), '_', '\\_'), '%')")
    fun searchProductByName(name: String): List<Product>

    fun getProductById(id: Int): Product
}