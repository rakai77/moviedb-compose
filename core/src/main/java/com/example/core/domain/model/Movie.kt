package com.example.core.domain.model

data class Movie(
    val page: Int,
    val totalPages: Int,
    val results: List<MovieItem>,
    val totalResults: Int
)

data class MovieItem(
    val overview: String? = null,
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val video: Boolean,
    val title: String? = null,
    val genreIds: List<Int>,
    val posterPath: String,
    val backdropPath: String,
    val releaseDate: String? = null,
    val popularity: Double? = null,
    val voteAverage: Double? = null,
    val id: Int,
    val adult: Boolean,
    val voteCount: Int? = null
)