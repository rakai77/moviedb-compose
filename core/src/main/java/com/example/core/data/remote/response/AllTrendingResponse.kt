package com.example.core.data.remote.response

import com.example.core.domain.model.AllTrending
import com.example.core.domain.model.AllTrendingItem
import com.google.gson.annotations.SerializedName

data class AllTrendingResponse(
	@field:SerializedName("page")
	val page: Int?,

	@field:SerializedName("totalPages")
	val totalPages: Int?,

	@field:SerializedName("results")
	val results: List<AllTrendingItemResponse>,

	@field:SerializedName("totalResults")
	val totalResults: Int?
)

data class AllTrendingItemResponse(

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("backdrop_path")
	val backdropPath: String,

	@field:SerializedName("media_type")
	val mediaType: String,

	@field:SerializedName("original_name")
	val originalName: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("original_title")
	val originalTitle: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("release_date")
	val releaseDate: String
)

fun AllTrendingResponse.toDomain() = AllTrending(
	totalPages = this.totalPages ?: 1,
	page = this.page ?: 0,
	results = this.results.map { it.toDomain() },
	totalResults = this.totalResults ?: 0
)

fun AllTrendingItemResponse.toDomain() = AllTrendingItem(
	this.posterPath,
	this.backdropPath,
	this.mediaType,
	this.originalName,
	this.name,
	this.id,
	this.originalTitle,
	this.title,
	this.releaseDate
)