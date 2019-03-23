package com.rogerio.xingtest.helpers

import com.rogerio.xingtest.services.models.GitRepo

object Factory {
    fun createRepos(): List<GitRepo> { return mutableListOf(createRepo()) }
    fun createRepo() = GitRepo()
}