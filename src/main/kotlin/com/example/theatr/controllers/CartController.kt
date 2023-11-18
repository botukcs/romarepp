package com.example.theatr.controllers

import com.example.theatr.entity.Cart
import com.example.theatr.entity.Product
import com.example.theatr.service.CartService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cart")
class CartController {

    @Autowired
    private lateinit var cartService: CartService

    val log: Logger = LoggerFactory.getLogger(this::class.java)

    @CrossOrigin(origins = ["http://localhost:3000"], allowCredentials = "true")
    @GetMapping("/getCartByUserId")
    fun getFavoriteByUserId(userId: Int): List<Product> {
        log.info("/getCartByUserId")
        return cartService.getOrderProductByUser(userId)
    }

    @CrossOrigin(origins = ["http://localhost:3000"], allowCredentials = "true")
    @PostMapping("/setCart")
    fun setCart(userId: Int, itemId: Int) {
        log.info("/setCart")
        cartService.setCart(userId, itemId)
    }

    @CrossOrigin(origins = ["http://localhost:3000"], allowCredentials = "true")
    @PostMapping("/deleteCart")
    fun deleteCart(userId: Int, itemId: Int) {
        log.info("/deleteCart")
        cartService.deleteCart(userId, itemId)
    }
}