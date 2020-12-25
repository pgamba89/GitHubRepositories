package com.example.githubrepositories.api

import com.example.githubrepositories.model.Repository
import com.google.gson.annotations.SerializedName

data class GitHubResponse(
        @SerializedName("total_count") val total: Int = 0,
        @SerializedName("items") val items: List<Repository> = emptyList(),
        val nextPage: Int? = null
)