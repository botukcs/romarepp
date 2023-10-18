package com.example.theatr.entity

import org.springframework.data.relational.core.mapping.Table

@Table("banner")
class Banner (val imageBase64: String, val textBanner: String): BaseEntity()