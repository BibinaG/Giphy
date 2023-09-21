package com.example.assignment.model.table

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "giphy_table")
data class TrendingTable(
    @PrimaryKey
    var id: String = "",
    var title: String? = null,
    var type: String? = null,
    var url: String? = null,
)