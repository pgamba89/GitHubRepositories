package com.example.githubrepositories.data

import androidx.paging.PagingSource
import com.example.githubrepositories.api.ApiService
import com.example.githubrepositories.model.Repository
import com.example.githubrepositories.utils.Constants.Companion.IN_QUALIFIER
import retrofit2.HttpException
import java.io.IOException

private const val GITHUB_STARTING_PAGE_INDEX = 1

class GithubPagingSource(
        private val service: ApiService,
        private val query: String
) : PagingSource<Int, Repository>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repository> {
        val position = params.key ?: GITHUB_STARTING_PAGE_INDEX
        val apiQuery = query + IN_QUALIFIER
        return try {
            val response = service.getRepositories(
                apiQuery,
                position,
                params.loadSize
            )
            val repos = response.items
            LoadResult.Page(
                    data = repos,
                    prevKey = if (position == GITHUB_STARTING_PAGE_INDEX) null else position - 1,
                    nextKey = if (repos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}