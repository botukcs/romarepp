package com.example.theatr.controllers

import com.example.theatr.entity.User
import com.example.theatr.service.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController {
    @Autowired
    private lateinit var userService: UserService

    val log: Logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping("/registration")
    fun regUser(login: String, password: String, email: String) {
        log.info("user/registration")
        userService.regUser(login, password, email)
    }

    @PostMapping("/changePassword")
    fun changePassword(userId: Int, password: String) {
        log.info("user/changePassword")
        userService.updateUserPassword(userId, password)
    }

    @GetMapping("/login")
    fun login(login: String, password: String): User {
        log.info("user/registration")
        return userService.getUserByLoginAndPassword(login, password)
    }

}