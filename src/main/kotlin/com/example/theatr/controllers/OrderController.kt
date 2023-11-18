package com.example.theatr.controllers

import com.example.theatr.entity.Cart
import com.example.theatr.entity.Order
import com.example.theatr.entity.Product
import com.example.theatr.service.CartService
import com.example.theatr.service.OrderService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/order")
class OrderController {

    @Autowired
    private lateinit var orderService: OrderService

    val log: Logger = LoggerFactory.getLogger(this::class.java)

    @CrossOrigin(origins = ["http://localhost:3000"], allowCredentials = "true")
    @GetMapping("/getOrderByUserId")
    fun getOrderByUserId(userId: Int): List<Product> {
        log.info("/getOrderByUserId")
        return orderService.getOrderProductByUser(userId)
    }

    @CrossOrigin(origins = ["http://localhost:3000"], allowCredentials = "true")
    @PostMapping("/setOrder")
    fun setOrder(userId: Int, itemId: Int) {
        log.info("/setOrder")
        orderService.setOrder(userId, itemId)
    }

    @CrossOrigin(origins = ["http://localhost:3000"], allowCredentials = "true")
    @PostMapping("/deleteOrder")
    fun deleteOrder(userId: Int, itemId: Int) {
        log.info("/deleteFavorite")
        orderService.deleteOrder(userId, itemId)
    }
}