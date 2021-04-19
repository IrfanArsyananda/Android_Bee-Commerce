package com.irfanarsya.beecommerce.local

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import io.reactivex.Single


@Dao
interface DaoHistory {

    @Query("SELECT * FROM history ORDER BY id DESC")
    fun getAllHistory(): List<History>

    @Insert
    fun insertHistory(history: History)

    @Delete
    fun deleteHistory(history: History)

}