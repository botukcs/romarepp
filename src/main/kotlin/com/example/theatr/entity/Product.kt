package com.example.theatr.entity

import org.springframework.data.relational.core.mapping.Table

@Table("product")
class Product(val name: String, val price: String, val itemUrl: String, val imageUrl: String, val lotCount: Int?) : BaseEntity();