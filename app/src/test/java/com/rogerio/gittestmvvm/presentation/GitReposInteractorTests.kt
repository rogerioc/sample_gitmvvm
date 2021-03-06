package com.rogerio.gittestmvvm.presentation

import android.content.Context
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.rogerio.gittestmvvm.feature.listRepos.presentation.ReposInteractor
import com.rogerio.gittestmvvm.feature.listRepos.presentation.model.GitRepoViewEntity
import com.rogerio.gittestmvvm.helpers.Factory
import com.rogerio.gittestmvvm.services.repository.GitReposDataSource
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GitReposInteractorTests {

    private lateinit var interactor: ReposInteractor

    @Mock
    private lateinit var reposRepository: GitReposDataSource

    @Mock
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = mock(Context::class.java)

        interactor = ReposInteractor(reposRepository)
    }

    @Test
    fun getReposByInteractor() {
        val page = 1
        val size = 1
        whenever(reposRepository.getRepos(page, size)).thenReturn(
            Single.just(
                Factory.createReposEntity()
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

    @Test
    fun getErrorReposByInteractor() {
        val page = 1
        val size = 1
        whenever(reposRepository.getRepos(page, size)).thenReturn(
            null
        )

        interactor.getRepos(page, size)
            .test()
            .assertError {
                it != null
            }

        verify(reposRepository).getRepos(page, size)
    }
}