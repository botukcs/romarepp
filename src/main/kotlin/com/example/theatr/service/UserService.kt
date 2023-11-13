package com.example.theatr.service

import com.example.theatr.entity.User
import com.example.theatr.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class UserService {
    @Autowired
    private lateinit var userRepository: UserRepository

    fun getUserByLoginAndPassword(login: String, password: String): User {

        val user = userRepository.getUserByLogin(login)
        if (password==user.password) {
            return userRepository.getUserByLogin(login)
        }
        return error("неверный пароль")
    }

    fun regUser(login: String, password: String, email: String){
        userRepository.save(User(login, password, email))
    }

    fun updateUserPassword(userId: Int, password: String){
        userRepository.updateUserPassword(userId, password)
    }
}