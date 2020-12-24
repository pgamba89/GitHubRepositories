package com.example.githubrepositories.model

data class Owner (
    val login: String,
    val id: Int,
    val avatar_url: String,
    val gravatar_id: String,
    val url: String,
    val company: String?,
    val location: String?,
    val bio: String?
)