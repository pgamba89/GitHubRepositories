package com.example.githubrepositories.api

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search/repositories?sort=stars")
    suspend fun getRepositories(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ): GitHubResponse
}