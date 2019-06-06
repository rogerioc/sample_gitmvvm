package com.rogerio.gittestmvvm.core

import android.content.Context
import androidx.room.Room
import com.rogerio.gittestmvvm.db.AppDatabase

class DataBase {
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "gitrepo_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}