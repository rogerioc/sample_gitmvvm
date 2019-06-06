package com.rogerio.gittestmvvm.core.helpers

import com.rogerio.gittestmvvm.feature.listRepos.presentation.model.GitRepoViewEntity
import com.rogerio.gittestmvvm.services.models.GitRepo

object Mapper {
    fun getGitViewEntity(item: GitRepo): GitRepoViewEntity = GitRepoViewEntity(item.fullName ?: "",
        item.owner?.login ?: "",
        item.description ?: "",
         item.fork ?: true,
         ownerUrl = item.owner.htmlUrl ?: "",
         repoUrl = item.htmlUrl ?: ""
        )

}