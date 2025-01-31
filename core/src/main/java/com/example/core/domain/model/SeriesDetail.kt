package com.example.core.domain.model

import com.example.core.data.remote.response.GenresResponse
import com.example.core.data.remote.response.ProductionCompaniesResponse

data class SeriesDetail(
    val numberOfEpisodes: Int,
    val networks: List<ProductionCompaniesResponse>,
    val backdropPath: String? = null,
    val genres: List<GenresResponse>,
    val popularity: Double? = null,
    val id: Int,
    val numberOfSeasons: Int,
    val voteCount: Int? = null,
    val firstAirDate: String? = null,
    val overview: String? = null,
    val languages: List<String>,
    val posterPath: String? = null,
    val originCountry: List<String>,
    val originalName: String? = null,
    val voteAverage: Double? = null,
    val name: String? = null,
    val tagline: String? = null,
    val adult: Boolean,
    val inProduction: Boolean,
    val homepage: String? = null,
)