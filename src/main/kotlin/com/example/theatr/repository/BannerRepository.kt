package com.example.theatr.repository

import com.example.theatr.entity.Banner
import com.example.theatr.entity.Product
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository

interface BannerRepository: CrudRepository<Banner, Long> {
    @Query("SELECT * FROM banner")
    fun getAllBanner(): List<Banner>
}