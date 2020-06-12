package com.lge.firstApp.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class User(
    val login: String,
    val name: String,
    val company: String,
    val location: String,
    @field:SerializedName("avatar_url") val avatarUrl: String,
    @field:SerializedName("public_repos") val publicRepos: Int,
    @field:SerializedName("created_at") val createdAt: Date,
    @field:SerializedName("updated_at") val updatedAt: Date
)