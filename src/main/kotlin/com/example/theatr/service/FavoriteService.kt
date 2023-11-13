package com.example.theatr.service

import com.example.theatr.entity.Favorite
import com.example.theatr.repository.FavoriteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.math.log

@Service
class FavoriteService {
    @Autowired
    private lateinit var favoriteRepository: FavoriteRepository

    fun getFavoriteByUserId(userId: Int): Favorite {
        return favoriteRepository.getFavoriteByUserId(userId)
    }

    fun setFavorite(userId: Int, itemId: Int) {
        try{
            val fav = getFavoriteByUserId(userId)
            val itemIds = fav.item_id+itemId
                favoriteRepository.updateFavItemByUser(userId, itemIds)
        }
        catch (e: Exception) {
            val itemList = listOf(itemId)
            favoriteRepository.newUserFavorite(userId, itemList)
        }
    }

    fun deleteFavorite(userId: Int, itemId: Int){
        val fav = getFavoriteByUserId(userId)
        val itemIds = fav.item_id.filter { id -> id != itemId }
        favoriteRepository.deleteFavItemByUser(userId, itemIds)
    }
}