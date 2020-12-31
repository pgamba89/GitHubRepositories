package com.example.githubrepositories.gitHubList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.example.githubrepositories.model.Owner
import com.example.githubrepositories.model.Repository
import com.example.githubrepositories.data.GitHubRepository
import com.example.githubrepositories.ui.gitHubList.GitHubModelView
import com.example.githubrepositories.utils.Constants.Companion.DEFAULT_QUERY
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext


@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class GitHubModelViewTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    fun runBlockingTest(
        context: CoroutineContext = EmptyCoroutineContext,
        testBody: suspend TestCoroutineScope.() -> Unit
    ): Unit {
    }

    @Mock
    private lateinit var repository: GitHubRepository

    @Spy
    private var repositoriesLiveData: MutableLiveData<PagingData<Repository>> = MutableLiveData()

    lateinit var modelView: GitHubModelView

    private val repos = listOf(
        Repository(
            1, "Hello", "", "Test", Owner(
                "1", 1, "", "", "", "", "", ""
            ), 1, ""
        )
    )

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        modelView = GitHubModelView(repository)
    }

    @Test
    fun getRepositories() = runBlockingTest {
        modelView.currentRepositories?.observeForever { }

        repositoriesLiveData.value = PagingData.from(repos)
        Mockito.`when`(repository.getRepositories(DEFAULT_QUERY)).thenReturn(repositoriesLiveData)

        assertEquals(repositoriesLiveData.value, modelView.currentRepositories?.value)
    }
}