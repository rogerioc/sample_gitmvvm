package com.rogerio.xingtest.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(GitRepoEntity::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun repoDao(): RepoDao
}