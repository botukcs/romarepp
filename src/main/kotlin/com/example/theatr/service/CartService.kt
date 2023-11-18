package com.example.theatr.service

import com.example.theatr.entity.Cart
import com.example.theatr.entity.Order
import com.example.theatr.entity.Product
import com.example.theatr.repository.CartRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CartService {
    @Autowired
    private lateinit var cartRepository: CartRepository

    @Autowired
    private lateinit var productService: ProductService

    fun getOrderByUserId(userId: Int): Cart {
        return cartRepository.getCartByUserId(userId)
    }

    fun getOrderProductByUser(userId: Int): List<Product> {
        val orderItemId = cartRepository.getCartByUserId(userId)
        val favProducts = mutableListOf<Product>()
        orderItemId.itemId.forEach {
            favProducts.add(productService.getProductById(it))
        }
        return favProducts
    }

    fun setCart(userId: Int, itemId: Int) {
        try {
            val fav = getOrderByUserId(userId)
            val itemIds = fav.itemId + itemId
            cartRepository.updateCartItemByUser(userId, itemIds)
        } catch (e: Exception) {
            val itemList = listOf(itemId)
            cartRepository.newUserCart(userId, itemList)
        }
    }

    fun deleteCart(userId: Int, itemId: Int) {
        val fav = getOrderByUserId(userId)
        val itemIds = fav.itemId.filter { id -> id != itemId }
        cartRepository.deleteCartItemByUser(userId, itemIds)
    }
}