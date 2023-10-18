package com.example.theatr.service

import com.example.theatr.entity.User
import com.example.theatr.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {
    @Autowired
    private lateinit var userRepository: UserRepository

    fun getUserByLogin(login: String): User {
       return userRepository.getUserByLogin(login)
    }

    fun regUser(login: String, password: String){
        userRepository.save(User(login, password))
    }
}