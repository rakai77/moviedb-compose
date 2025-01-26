package com.example.core.domain.model

data class Series(
    val page: Int,
    val totalPages: Int,
    val results: List<SeriesItem>,
    val totalResults: Int
)

data class SeriesItem(
    val firstAirDate: String,
    val overview: String,
    val originalLanguage: String? = null,
    val genreIds: List<Int>,
    val posterPath: String,
    val originCountry: List<String>,
    val backdropPath: String,
    val originalName: String? = null,
    val popularity: Double? = null,
    val voteAverage: Double? = null,
    val name: String? = null,
    val id: Int,
    val adult: Boolean,
    val voteCount: Int? = null
)