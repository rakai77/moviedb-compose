package com.example.core.data.remote.response

import com.example.core.domain.model.Movie
import com.example.core.domain.model.MovieItem
import com.google.gson.annotations.SerializedName

data class MovieResponse(

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("total_pages")
	val totalPages: Int,

	@field:SerializedName("results")
	val results: List<MovieItemResponse>,

	@field:SerializedName("total_results")
	val totalResults: Int
)

data class MovieItemResponse(

	@field:SerializedName("overview")
	val overview: String? = null,

	@field:SerializedName("original_language")
	val originalLanguage: String? = null,

	@field:SerializedName("original_title")
	val originalTitle: String? = null,

	@field:SerializedName("video")
	val video: Boolean,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("genre_ids")
	val genreIds: List<Int>,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("backdrop_path")
	val backdropPath: String,

	@field:SerializedName("release_date")
	val releaseDate: String? = null,

	@field:SerializedName("popularity")
	val popularity: Double? = null,

	@field:SerializedName("vote_average")
	val voteAverage: Double? = null,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("adult")
	val adult: Boolean,

	@field:SerializedName("vote_count")
	val voteCount: Int? = null
)


fun MovieResponse.toDomain() = Movie(
	page = page,
	totalPages = totalPages,
	results = results.map { it.toDomain() },
	totalResults = totalResults
)

fun MovieItemResponse.toDomain() = MovieItem(
	overview = this.overview,
	originalLanguage = this.originalLanguage,
	originalTitle = this.originalTitle,
	video = this.video,
	title = this.title,
	genreIds = this.genreIds,
	posterPath = this.posterPath,
	backdropPath = this.backdropPath,
	releaseDate = this.releaseDate,
	popularity = this.popularity,
	voteAverage = this.voteAverage,
	id = this.id,
	adult = this.adult,
)
