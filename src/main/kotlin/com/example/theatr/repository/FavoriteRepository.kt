package com.example.theatr.repository

import com.example.theatr.entity.Favorite
import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository

interface FavoriteRepository: CrudRepository<Favorite, Long> {

    fun getFavoriteByUserId(userId: Int): Favorite

    @Modifying
    @Query("INSERT INTO favorite(user_id, item_id) VALUES (:userId, ARRAY[:itemId])")
    fun newUserFavorite(userId: Int, itemId: List<Int>)

    @Modifying
    @Query("UPDATE favorite SET item_id= ARRAY[:itemId] WHERE user_id= :userId")
    fun updateFavItemByUser(userId: Int, itemId: List<Int>)

    @Modifying
    @Query("UPDATE favorite SET item_id= ARRAY[:itemId] WHERE user_id= :userId")
    fun deleteFavItemByUser(userId: Int, itemId: List<Int>)
}