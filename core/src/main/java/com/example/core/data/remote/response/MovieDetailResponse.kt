package com.example.core.data.remote.response

import com.example.core.domain.model.Genres
import com.example.core.domain.model.MovieDetail
import com.example.core.domain.model.ProductionCompanies
import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(

	@field:SerializedName("original_language")
	val originalLanguage: String? = null,

	@field:SerializedName("imdb_id")
	val imdbId: String? = null,

	@field:SerializedName("video")
	val video: Boolean,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("backdrop_path")
	val backdropPath: String? = null,

	@field:SerializedName("genres")
	val genres: List<GenresResponse>,

	@field:SerializedName("popularity")
	val popularity: Double? = null,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("vote_count")
	val voteCount: Int? = null,

	@field:SerializedName("overview")
	val overview: String? = null,

	@field:SerializedName("original_title")
	val originalTitle: String? = null,

	@field:SerializedName("poster_path")
	val posterPath: String? = null,

	@field:SerializedName("origin_country")
	val originCountry: List<String>,

	@field:SerializedName("production_companies")
	val productionCompanies: List<ProductionCompaniesResponse>,

	@field:SerializedName("release_date")
	val releaseDate: String? = null,

	@field:SerializedName("vote_average")
	val voteAverage: Double? = null,

	@field:SerializedName("tagline")
	val tagline: String? = null,

	@field:SerializedName("adult")
	val adult: Boolean,

	@field:SerializedName("homepage")
	val homepage: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class GenresResponse(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)


data class ProductionCompaniesResponse(

	@field:SerializedName("logo_path")
	val logoPath: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("origin_country")
	val originCountry: String? = null
)

fun MovieDetailResponse.toDomain() = MovieDetail(
	originalLanguage = this.originalLanguage,
	imdbId = this.imdbId,
	video = this.video,
	title = this.title,
	backdropPath = this.backdropPath,
	genres = this.genres.map { it.toDomain() },
	popularity = this.popularity,
	id = this.id,
	voteCount = this.voteCount,
	overview = this.overview,
	originalTitle = this.originalTitle,
	posterPath = this.posterPath,
	originCountry = this.originCountry,
	productionCompanies = this.productionCompanies.map { it.toDomain() },
	releaseDate = this.releaseDate,
	voteAverage = this.voteAverage,
	tagline = this.tagline,
	adult = this.adult,
	homepage = this.homepage,
	status = this.status
)

fun GenresResponse.toDomain() = Genres(
	name = this.name ?: "",
	id = this.id ?: 0
)

fun ProductionCompaniesResponse.toDomain() = ProductionCompanies(
	logoPath = this.logoPath,
	name = this.name,
	id = this.id,
	originCountry = this.originCountry
)