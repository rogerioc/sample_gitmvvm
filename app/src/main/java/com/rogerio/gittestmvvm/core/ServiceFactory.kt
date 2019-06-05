package com.rogerio.xingtest.core

import com.rogerio.xingtest.services.GitService
import com.rogerio.xingtest.services.repository.GitReposDataSource
import com.rogerio.xingtest.services.repository.GitReposRepository

object ServiceFactory {
    public fun repositoryFactory(): GitReposDataSource {
        return GitReposRepository(GitService().getService())
    }
}