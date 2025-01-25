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
    val originalName: String,
    val name: String,
    val id: Int,
    val originalTitle: String,
    val title: String,
    val releaseDate: String
)