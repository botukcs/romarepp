package com.example.theatr.repository

import com.example.theatr.entity.User
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, Long> {
    fun getUserByLogin(login: String): User
}