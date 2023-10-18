package com.example.theatr.service

import com.example.theatr.entity.Banner
import com.example.theatr.entity.Product
import com.example.theatr.repository.BannerRepository
import com.example.theatr.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BannerService {
    @Autowired
    private lateinit var bannerRepository: BannerRepository

    fun saveBanner( banner: Banner){
        bannerRepository.save(banner)
    }

    fun getAllBanner(): List<Banner>{
        return bannerRepository.getAllBanner()
    }
}