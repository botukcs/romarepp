package com.example.theatr.service

import com.example.theatr.entity.Favorite
import com.example.theatr.entity.Product
import com.example.theatr.repository.FavoriteRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.math.log

@Service
class FavoriteService {
    val log: Logger = LoggerFactory.getLogger(this::class.java)

    @Autowired
    private lateinit var favoriteRepository: FavoriteRepository

    @Autowired
    private lateinit var productService: ProductService

    fun getFavoriteByUserId(userId: Int): Favorite {
        return favoriteRepository.getFavoriteByUserId(userId)
    }

    fun getFavoriteByUser(userId: Int): List<Product> {
        val favItemId = favoriteRepository.getFavoriteByUserId(userId)

        val favProducts = mutableListOf<Product>()
        favItemId.itemId.forEach {
            favProducts.add(productService.getProductById(it))
        }
        return favProducts
    }

    fun setFavorite(userId: Int, itemId: Int) {
        try{
            val fav = getFavoriteByUserId(userId)
            val itemIds = fav.itemId+itemId
                favoriteRepository.updateFavItemByUser(userId, itemIds)
        }
        catch (e: Exception) {
            val itemList = listOf(itemId)
            favoriteRepository.newUserFavorite(userId, itemList)
        }
    }

    fun deleteFavorite(userId: Int, itemId: Int){
        val fav = getFavoriteByUserId(userId)
        val itemIds = fav.itemId.filter { id -> id != itemId }
        favoriteRepository.deleteFavItemByUser(userId, itemIds)
    }
}