package com.rogerio.xingtest.core.helpers

import com.rogerio.xingtest.db.GitRepoEntity
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
    public fun gitViewEntityToGitRepoEntity(item: GitRepoViewEntity): GitRepoEntity = GitRepoEntity(
        name = item.name,
        desciption = item.desciption,
        flag = item.flag,
        repoUrl = item.repoUrl,
        ownerUrl = item.ownerUrl,
        ownerName = item.ownerName
    )

    public fun gitRepoEntityToGitViewEntity(item: GitRepoEntity): GitRepoViewEntity = GitRepoViewEntity(
        name = item.name,
        desciption = item.desciption,
        flag = item.flag,
        repoUrl = item.repoUrl,
        ownerUrl = item.ownerUrl,
        ownerName = item.ownerName
    )
}