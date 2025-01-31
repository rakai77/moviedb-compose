package com.example.core.data.remote.response

import com.example.core.domain.model.SeriesDetail
import com.google.gson.annotations.SerializedName

data class SeriesDetailResponse(

	@field:SerializedName("number_of_episodes")
	val numberOfEpisodes: Int? = null,

	@field:SerializedName("networks")
	val networks: List<ProductionCompaniesResponse>,

	@field:SerializedName("backdrop_path")
	val backdropPath: String? = null,

	@field:SerializedName("genres")
	val genres: List<GenresResponse>,

	@field:SerializedName("popularity")
	val popularity: Double? = null,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("number_of_seasons")
	val numberOfSeasons: Int? = null,

	@field:SerializedName("vote_count")
	val voteCount: Int? = null,

	@field:SerializedName("first_air_date")
	val firstAirDate: String? = null,

	@field:SerializedName("overview")
	val overview: String? = null,

	@field:SerializedName("languages")
	val languages: List<String>,

	@field:SerializedName("poster_path")
	val posterPath: String? = null,

	@field:SerializedName("origin_country")
	val originCountry: List<String>,

	@field:SerializedName("original_name")
	val originalName: String? = null,

	@field:SerializedName("vote_average")
	val voteAverage: Double? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("tagline")
	val tagline: String? = null,

	@field:SerializedName("adult")
	val adult: Boolean,

	@field:SerializedName("in_production")
	val inProduction: Boolean,

	@field:SerializedName("homepage")
	val homepage: String? = null,
)

fun SeriesDetailResponse.toDomain() = SeriesDetail(
	numberOfEpisodes = this.numberOfEpisodes ?: 0,
	networks = this.networks,
	backdropPath = this.backdropPath,
	genres = this.genres,
	popularity = this.popularity,
	id = this.id,
	numberOfSeasons = this.numberOfSeasons ?: 0,
	voteCount = this.voteCount,
	firstAirDate = this.firstAirDate,
	overview = this.overview,
	languages = this.languages,
	posterPath = this.posterPath,
	originCountry = this.originCountry,
	originalName = this.originalName,
	voteAverage = this.voteAverage,
	name = this.name,
	tagline = this.tagline,
	adult = this.adult,
	inProduction = this.inProduction,
	homepage = this.homepage,
)