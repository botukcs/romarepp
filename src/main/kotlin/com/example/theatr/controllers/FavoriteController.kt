package com.example.theatr.controllers

import com.example.theatr.entity.Favorite
import com.example.theatr.service.FavoriteService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/favorite")
class FavoriteController {

    @Autowired
    private lateinit var favoriteService: FavoriteService

    val log: Logger = LoggerFactory.getLogger(this::class.java)

    @CrossOrigin(origins = ["http://localhost:3000"], allowCredentials = "true")
    @GetMapping("/getFavoriteByUserId")
    fun getFavoriteByUserId(userId: Int): Favorite {
        log.info("/getFavoriteByUserId")
        return favoriteService.getFavoriteByUserId(userId)
    }

    @CrossOrigin(origins = ["http://localhost:3000"], allowCredentials = "true")
    @PostMapping("/setFavorite")
    fun setFavorite(userId: Int, itemId: Int) {
        log.info("/setFavorite")
        favoriteService.setFavorite(userId, itemId)
    }

    @CrossOrigin(origins = ["http://localhost:3000"], allowCredentials = "true")
    @PostMapping("/deleteFavorite")
    fun deleteFavorite(userId: Int, itemId: Int) {
        log.info("/deleteFavorite")
        favoriteService.deleteFavorite(userId, itemId)
    }
}