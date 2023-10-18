package com.example.theatr.utils

import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class ToListRowMapper: RowMapper<List<Any?>> {
    override fun mapRow(rs: ResultSet, rowNum: Int): List<Any?> {
        val list = mutableListOf<Any?>()
        for (i in 1..rs.metaData.columnCount)
            list.add(rs.getObject(i))
        return list
    }
}