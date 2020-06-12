package com.lge.firstApp.model

import com.google.gson.annotations.SerializedName


data class Repo(
    val name: String,
    @field:SerializedName("full_name") val fullName: String,
    val private: Boolean,
    val owner: Owner,
    val description: String,
    val company: String?
) {
    data class Owner(
        val login: String,
        val type: String,
        @field:SerializedName("avatar_url") val avatarUrl: String
    )
}

data class SearchResult(
    @field:SerializedName("total_count") val totalCount: Int,
    @field:SerializedName("incomplete_results") val incompleteResults: Boolean,
    val items: List<Repo>
)