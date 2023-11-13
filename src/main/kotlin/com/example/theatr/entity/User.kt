package com.example.theatr.entity

import org.springframework.data.relational.core.mapping.Table

@Table("user")
class User (val login: String, val password: String, val email: String) : BaseEntity();
