package com.example.theatr.entity

import org.springframework.data.annotation.Id

abstract class BaseEntity  (
    @Id var id: Long? = null,
    ) {
    final override fun toString() = "${this::class.simpleName} ${id ?: "(no ID)"}"

    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BaseEntity

        if (id != other.id) return false

        return true
    }

    final override fun hashCode(): Int = 23
}