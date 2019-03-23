package com.rogerio.xingtest

import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.rogerio.xingtest.feature.listRepos.presentation.ReposInteractor
import com.rogerio.xingtest.feature.listRepos.presentation.model.GitRepoViewEntity
import com.rogerio.xingtest.helpers.Factory
import com.rogerio.xingtest.services.repository.GitReposDataSource
import com.rogerio.xingtest.services.repository.GitReposRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GitReposInteractorTests {

    private lateinit var interactor: ReposInteractor

    @Mock
    private lateinit var reposRepository: GitReposDataSource

    @Before
    fun setUp() {
        interactor = ReposInteractor(reposRepository)
    }

    @Test
    fun getReposByInteractor() {
        val page = 1
        val size = 1
        whenever(reposRepository.getRepos(page, size)).thenReturn(
            Single.just(
                Factory.createRepos()
            )
        )

        interactor.getRepos(page, size)
            .test()
            .assertValue {
                it.size == 1
                it is List<GitRepoViewEntity>
            }

        verify(reposRepository).getRepos(page, size)
    }
}