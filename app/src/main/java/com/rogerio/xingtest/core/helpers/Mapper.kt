package com.rogerio.xingtest.core.helpers

import com.rogerio.xingtest.feature.listRepos.presentation.model.GitRepoViewEntity
import com.rogerio.xingtest.services.models.GitRepo

object Mapper {
    public fun getGitViewEntity(item: GitRepo): GitRepoViewEntity = GitRepoViewEntity(item.fullName ?: "",
        item.owner?.login ?: "",
        item.description ?: "",
         item.fork ?: true,
         ownerUrl = item.owner.htmlUrl ?: "",
         repoUrl = item.htmlUrl ?: ""
        )
}