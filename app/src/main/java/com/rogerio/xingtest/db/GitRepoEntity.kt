package com.rogerio.xingtest.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GitRepoEntity(
    @PrimaryKey
    var id: Int = 0,
    val name: String = "",
    val ownerName: String = "",
    val desciption: String = "",
    val flag: Boolean = false,
    val loading: Boolean = false,
    val ownerUrl: String = "",
    val repoUrl: String = ""
)
