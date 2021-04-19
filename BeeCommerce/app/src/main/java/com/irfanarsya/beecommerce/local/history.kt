package com.irfanarsya.beecommerce.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class History(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Int? = null,

        @ColumnInfo(name = "keyword")
        var keyword: String? = null,

        @ColumnInfo(name = "date")
        var date: String? = null

)