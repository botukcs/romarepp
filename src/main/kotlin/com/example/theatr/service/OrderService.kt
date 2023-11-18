package com.example.theatr.service

import com.example.theatr.entity.Cart
import com.example.theatr.entity.Favorite
import com.example.theatr.entity.Order
import com.example.theatr.entity.Product
import com.example.theatr.repository.CartRepository
import com.example.theatr.repository.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrderService {
    @Autowired
    private lateinit var orderRepository: OrderRepository

    @Autowired
    private lateinit var productService: ProductService

    fun getOrderByUserId(userId: Int): Order {
        return orderRepository.getOrderByUserId(userId)
    }

    fun getOrderProductByUser(userId: Int): List<Product> {
        val orderItemId = orderRepository.getOrderByUserId(userId)
        val favProducts = mutableListOf<Product>()
        orderItemId.itemId.forEach {
            favProducts.add(productService.getProductById(it))
        }
        return favProducts
    }

    fun setOrder(userId: Int, itemId: Int) {
        try{
            val fav = getOrderByUserId(userId)
            val itemIds = fav.itemId+itemId
            orderRepository.updateOrderItemByUser(userId, itemIds)
        }
        catch (e: Exception) {
            val itemList = listOf(itemId)
            orderRepository.newUserOrder(userId, itemList)
        }
    }

    fun deleteOrder(userId: Int, itemId: Int) {
        val fav = getOrderByUserId(userId)
        val itemIds = fav.itemId.filter { id -> id != itemId }
        orderRepository.deleteOrderItemByUser(userId, itemIds)
    }
}