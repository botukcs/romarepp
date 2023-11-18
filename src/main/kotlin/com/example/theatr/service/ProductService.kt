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

    fun getAllProduct(firstId: Int, fieldName: String, sortType: String): List<Product>{
        return productRepository.getAllProduct(firstId, fieldName, sortType)
    }

    fun getProductById(id: Int): Product{
        return productRepository.getProductById(id)
    }

    fun searchProductByName(name: String) : List<Product>{
        return productRepository.searchProductByName(name)
    }
}