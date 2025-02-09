package com.example.core.domain.model

data class MovieDetail(
    val originalLanguage: String? = null,
    val imdbId: String? = null,
    val video: Boolean,
    val title: String? = null,
    val backdropPath: String? = null,
    val genres: List<Genres>,
    val popularity: Double? = null,
    val id: Int,
    val voteCount: Int? = null,
    val overview: String? = null,
    val originalTitle: String? = null,
    val posterPath: String? = null,
    val originCountry: List<String>,
    val productionCompanies: List<ProductionCompanies>,
    val releaseDate: String? = null,
    val voteAverage: Double? = null,
    val tagline: String? = null,
    val adult: Boolean,
    val homepage: String? = null,
    val status: String? = null,
    val runtime: Int? = null
)

data class Genres(
    val name: String,
    val id: Int
)

data class ProductionCompanies(
    val logoPath: String? = null,
    val name: String? = null,
    val id: Int,
    val originCountry: String? = null
)

