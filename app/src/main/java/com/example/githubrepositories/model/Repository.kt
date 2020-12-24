package com.example.githubrepositories.model

data class Repository(
    val id: Int,
    val name: String,
    val fullName: String,
    val description: String?,
    val owner: Owner,
    val stars: Int,
    val language : String?
)