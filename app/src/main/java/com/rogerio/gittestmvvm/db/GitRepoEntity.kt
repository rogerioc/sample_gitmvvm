package com.rogerio.gittestmvvm.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GitRepoEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val name: String = "",
    val ownerName: String = "",
    val description: String = "",
    val flag: Boolean = false,
    val loading: Boolean = false,
    val ownerUrl: String = "",
    val repoUrl: String = ""
)

