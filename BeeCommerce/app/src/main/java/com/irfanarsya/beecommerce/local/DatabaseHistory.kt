package com.irfanarsya.beecommerce.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(History::class), version = 1)
abstract class DatabaseHistory : RoomDatabase() {

    abstract fun historyDao(): DaoHistory

    companion object {
        private var INSTANCE: DatabaseHistory? = null
        fun getInstance(context: Context): DatabaseHistory? {
            if (INSTANCE == null) {
                synchronized(DatabaseHistory::class) {
                    INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            DatabaseHistory::class.java,
                            "dbhistory.db"
                    ).build()
                }
            }
            return INSTANCE
        }
    }

}