package com.rogerio.xingtest

import com.nhaarman.mockitokotlin2.whenever
import com.rogerio.xingtest.helpers.Factory
import com.rogerio.xingtest.services.IGitService
import com.rogerio.xingtest.services.repository.GitReposDataSource
import com.rogerio.xingtest.services.repository.GitReposRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GitReposRepositoryTests {
    @Mock
    private lateinit var service: IGitService

    private lateinit var repository: GitReposDataSource

    @Before
    fun setUp() {
        repository = GitReposRepository(service)
    }

    @Test
    fun getRepos() {
        val page = 1
        val size = 1
        whenever(service.getRepos(page, size)).thenReturn(
            Single.just(
                Factory.createRepos()
            )
        )

        repository.getRepos(page,size)

        verify(service).getRepos(page, size)
    }
}