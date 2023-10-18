package com.example.theatr.service

import com.example.theatr.entity.Product
import com.example.theatr.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService {
    @Autowired
    private lateinit var productRepository: ProductRepository

    fun saveProduct(product: Product){
        productRepository.save(product)
    }

    fun getAllProduct(): List<Product>{
        return productRepository.getAllProduct()
    }
}