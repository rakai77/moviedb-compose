package com.example.core.data.remote.response

import com.example.core.domain.model.Series
import com.example.core.domain.model.SeriesItem
import com.google.gson.annotations.SerializedName

data class SeriesResponse(

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("total_pages")
	val totalPages: Int,

	@field:SerializedName("results")
	val results: List<SeriesItemResponse>,

	@field:SerializedName("total_results")
	val totalResults: Int
)

data class SeriesItemResponse(

	@field:SerializedName("first_air_date")
	val firstAirDate: String,

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("original_language")
	val originalLanguage: String? = null,

	@field:SerializedName("genre_ids")
	val genreIds: List<Int>,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("origin_country")
	val originCountry: List<String>,

	@field:SerializedName("backdrop_path")
	val backdropPath: String,

	@field:SerializedName("original_name")
	val originalName: String? = null,

	@field:SerializedName("popularity")
	val popularity: Double? = null,

	@field:SerializedName("vote_average")
	val voteAverage: Double? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("adult")
	val adult: Boolean,

	@field:SerializedName("vote_count")
	val voteCount: Int? = null
)

fun SeriesResponse.toDomain() = Series(
	page = this.page,
	totalPages = this.totalPages,
	results = this.results.map { it.toDomain() },
	totalResults = this.totalResults
)

fun SeriesItemResponse.toDomain() = SeriesItem(
	firstAirDate = this.firstAirDate,
	overview = this.overview,
	originalLanguage = this.originalLanguage,
	genreIds = this.genreIds,
	posterPath = this.posterPath,
	originCountry = this.originCountry,
	backdropPath = this.backdropPath,
	originalName = this.originalName,
	popularity = this.popularity,
	voteAverage = this.voteAverage,
	name = this.name,
	id = this.id,
	adult = this.adult,
	voteCount = this.voteCount
)
