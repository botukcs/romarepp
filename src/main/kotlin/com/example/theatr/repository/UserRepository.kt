package com.example.theatr.repository

import com.example.theatr.entity.User
import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, Long> {
    fun getUserByLogin(login: String): User

    @Modifying
    @Query("UPDATE user SET password= :password WHERE user_id= :userId")
    fun updateUserPassword(userId: Int, password: String)
}