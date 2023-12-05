package com.example.theatr.controllers

import com.example.theatr.entity.Product
import com.example.theatr.service.EmailService
import com.example.theatr.service.FavoriteService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/email")
class EmailController {

    @Autowired
    private lateinit var emailService: EmailService

    val log: Logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping("/send")
    fun getAllProducts(to: String, subject: String, body: String) {
        log.info("/getAllProducts")
        emailService.sendEmail(to, subject, body)
    }
}