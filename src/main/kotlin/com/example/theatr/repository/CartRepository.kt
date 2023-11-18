package com.example.theatr.repository

import com.example.theatr.entity.Cart
import com.example.theatr.entity.Favorite
import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository

interface CartRepository  : CrudRepository<Cart, Long> {

    fun getCartByUserId(userId: Int): Cart

    @Modifying
    @Query("INSERT INTO cart(user_id, item_id) VALUES (:userId, ARRAY[:itemId])")
    fun newUserCart(userId: Int, itemId: List<Int>)

    @Modifying
    @Query("UPDATE cart SET item_id= ARRAY[:itemId] WHERE user_id= :userId")
    fun updateCartItemByUser(userId: Int, itemId: List<Int>)

    @Modifying
    @Query("UPDATE cart SET item_id= ARRAY[:itemId] WHERE user_id= :userId")
    fun deleteCartItemByUser(userId: Int, itemId: List<Int>)
}