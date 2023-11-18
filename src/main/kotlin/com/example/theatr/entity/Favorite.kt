package com.example.theatr.entity

import org.springframework.data.relational.core.mapping.Table

@Table("favorite")
class Favorite(val userId: Int, val itemId: List<Int>) : BaseEntity();