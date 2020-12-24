package com.example.githubrepositories.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.githubrepositories.api.ApiService
import com.example.githubrepositories.data.GithubPagingSource
import com.example.githubrepositories.model.Repository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitHubRepository @Inject constructor(
    private val apiService: ApiService
) {
    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }

    fun getRepositories(queryString: String): LiveData<PagingData<Repository>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { GithubPagingSource(apiService, queryString) }
        ).liveData
    }
}