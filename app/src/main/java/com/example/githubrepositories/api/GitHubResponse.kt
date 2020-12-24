package com.example.githubrepositories.api

import com.example.githubrepositories.model.Repository

data class GitHubResponse(
        val total: Int = 0,
        val items: List<Repository>  = emptyList(),
        val nextPage: Int? = null
)