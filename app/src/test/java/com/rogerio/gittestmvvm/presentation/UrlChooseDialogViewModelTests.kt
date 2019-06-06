package com.rogerio.gittestmvvm.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.rogerio.gittestmvvm.feature.listRepos.presentation.dialog.UrlChooseDialogViewModel
import com.rogerio.gittestmvvm.feature.listRepos.presentation.model.GitRepoViewEntity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UrlChooseDialogViewModelTests {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: UrlChooseDialogViewModel

    private lateinit var gitRepoViewEntity: GitRepoViewEntity

    @Mock
    private lateinit var observer: Observer<String>

    val ownerUrl = "owner"
    val repoUrl = "repoUrl"

    @Before
    fun setUp(){
        gitRepoViewEntity = GitRepoViewEntity(ownerUrl = ownerUrl,
            repoUrl = repoUrl
        )
        viewModel = UrlChooseDialogViewModel(gitRepoViewEntity)
    }

    @Test
    fun selectOwnerUrl() {
        viewModel.event.observeForever(observer)
        viewModel.onOwnerClicked()
        var value = viewModel.event.value

        assert(value == ownerUrl)
    }

    @Test
    fun selectRepoUrl() {
        viewModel.event.observeForever(observer)
        viewModel.onRepoClicked()
        var value = viewModel.event.value

        assert(value == repoUrl)
    }
}