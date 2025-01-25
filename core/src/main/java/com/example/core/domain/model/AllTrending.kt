package com.example.core.domain.model

data class AllTrending(
    val page: Int,
    val totalPages: Int,
    val results: List<AllTrendingItem>,
    val totalResults: Int
)

data class AllTrendingItem(
    val posterPath: String,
    val backdropPath: String,
    val mediaType: String,
    val originalName: String? = null,
    val name: String? = null,
    val id: Int,
    val originalTitle: String? = null,
    val title: String? = null,
    val releaseDate: String? = null,
    val firstAirDate: String? = null
)