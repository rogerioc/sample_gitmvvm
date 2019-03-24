package com.rogerio.xingtest.presentation

 import android.content.Context
 import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.rogerio.xingtest.R
import com.rogerio.xingtest.core.InfoError
import com.rogerio.xingtest.core.helpers.Mapper
import com.rogerio.xingtest.feature.listRepos.presentation.ReposInteractor
import com.rogerio.xingtest.feature.listRepos.presentation.StartViewModel
import com.rogerio.xingtest.feature.listRepos.presentation.model.GitRepoViewEntity
import com.rogerio.xingtest.helpers.Factory
import com.rogerio.xingtest.helpers.RxImmediateSchedulerRule
import com.rogerio.xingtest.services.repository.GitReposDataSource
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class StartViewModelTests {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private lateinit var repository: GitReposDataSource

    @Mock
    lateinit var observer: Observer<GitRepoViewEntity>

    @Mock
    lateinit var  observerInfoError: Observer<InfoError>

    private lateinit var interactor: ReposInteractor

    private lateinit var viewModel: StartViewModel

    @Mock
    private lateinit var context: Context

    @Before
    fun setUp() {
        interactor = ReposInteractor(repository,context)
        viewModel = StartViewModel(interactor)
    }

    @Test
    fun startViewModel() {
        val page = 1
        val size = 10
        val repos = Factory.createRepos()
        whenever(repository.getRepos(page, size)).thenReturn(
            Single.just(
                repos
            )
        )
        viewModel.start()
        verify(repository).getRepos(page,size)
        viewModel.repositories != null
        viewModel.repositories.size > 0
    }

    @Test
    fun selectedGitRepo() {
        var gitRepo = Factory.createRepo()
        var gitViewRepo = Mapper.getGitViewEntity(gitRepo)
        viewModel.selectedGitRepo.observeForever(observer)
        viewModel.clickOnGitRepo(gitViewRepo)

        assert( viewModel.selectedGitRepo.value == gitViewRepo)
    }

    @Test
    fun showErrorMessage() {
        val page = 1
        val size = 10
        whenever(repository.getRepos(page, size)).thenReturn(
           null
        )

        viewModel.errorMessageEvent.observeForever(observerInfoError)
        viewModel.start()

        assert(viewModel.errorMessageEvent.value?.error ?: 0 == R.string.default_error)

    }

}