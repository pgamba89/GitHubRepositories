package com.example.githubrepositories.repository

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.example.githubrepositories.api.ApiService
import com.example.githubrepositories.data.GithubRemoteMediator
import com.example.githubrepositories.db.RepoDatabase
import com.example.githubrepositories.model.Repository
import com.example.githubrepositories.utils.OpenForTesting
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@OpenForTesting
class GitHubRepository @Inject constructor(
    private val apiService: ApiService,
    private val database: RepoDatabase
) {
    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }

    @OptIn(ExperimentalPagingApi::class)
    fun getRepositories(queryString: String): LiveData<PagingData<Repository>> {

        val dbQuery = "%${queryString.replace(' ', '%')}%"
        val pagingSourceFactory = { database.reposDao().reposByName(dbQuery) }

        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            remoteMediator = GithubRemoteMediator(
                queryString,
                apiService,
                database
            ),
            pagingSourceFactory = pagingSourceFactory
        ).liveData
    }
}