package com.example.githubrepositories.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Owner(
    @field:SerializedName("login") val login: String,
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("avatar_url") val avatar_url: String,
    @field:SerializedName("gravatar_id") val gravatar_id: String,
    @field:SerializedName("url") val url: String,
    @field:SerializedName("company") val company: String?,
    @field:SerializedName("location") val location: String?,
    @field:SerializedName("bio") val bio: String?
) : Parcelable