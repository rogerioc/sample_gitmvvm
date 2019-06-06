package com.rogerio.gittestmvvm.core

import android.content.Context
import com.rogerio.gittestmvvm.services.GitService
import com.rogerio.gittestmvvm.services.repository.GitReposDataSource
import com.rogerio.gittestmvvm.services.repository.GitReposRepository

object ServiceFactory {
    fun repositoryFactory(context: Context): GitReposDataSource {
        return GitReposRepository(GitService().getService(), context)
    }
}