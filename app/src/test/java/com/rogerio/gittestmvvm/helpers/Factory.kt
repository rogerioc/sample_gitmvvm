package com.rogerio.gittestmvvm.helpers

import com.rogerio.gittestmvvm.core.helpers.Mapper
import com.rogerio.gittestmvvm.db.GitRepoEntity
import com.rogerio.gittestmvvm.services.models.GitRepo

object Factory {
    fun createRepos(): List<GitRepo> {
        return mutableListOf(createRepo())
    }

    fun createRepo() = GitRepo()

    fun createReposEntity(): List<GitRepoEntity> {
        return mutableListOf(createRepoEntity())
    }

    fun createRepoEntity() = GitRepoEntity(name = "Teste")
    fun createReposView(repos: List<GitRepo>) = repos.map {
        Mapper.getGitViewEntity(it)
    }
}