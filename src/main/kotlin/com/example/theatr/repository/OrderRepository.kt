package com.example.theatr.repository

import com.example.theatr.entity.Cart
import com.example.theatr.entity.Favorite
import com.example.theatr.entity.Order
import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository

interface OrderRepository  : CrudRepository<Order, Long> {

    fun getOrderByUserId(userId: Int): Order

    @Modifying
    @Query("INSERT INTO order(user_id, item_id) VALUES (:userId, ARRAY[:itemId])")
    fun newUserOrder(userId: Int, itemId: List<Int>)

    @Modifying
    @Query("UPDATE order SET item_id= ARRAY[:itemId] WHERE user_id= :userId")
    fun updateOrderItemByUser(userId: Int, itemId: List<Int>)

    @Modifying
    @Query("UPDATE order SET item_id= ARRAY[:itemId] WHERE user_id= :userId")
    fun deleteOrderItemByUser(userId: Int, itemId: List<Int>)
}