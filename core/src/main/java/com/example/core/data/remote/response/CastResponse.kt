package com.example.core.data.remote.response

import com.example.core.domain.model.Cast
import com.example.core.domain.model.CastItem
import com.google.gson.annotations.SerializedName

data class CastResponse(

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("total_pages")
	val totalPages: Int,

	@field:SerializedName("results")
	val results: List<CastItemResponse>,

	@field:SerializedName("total_results")
	val totalResults: Int
)

data class CastItemResponse(

	@field:SerializedName("original_name")
	val originalName: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("profile_path")
	val profilePath: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,
)

fun CastResponse.toDomain() = Cast(
	page = this.page,
	totalPages = this.totalPages,
	results = this.results.map { it.toDomain() },
	totalResults = this.totalResults
)

fun CastItemResponse.toDomain() = CastItem(
	originalName = this.originalName,
	name = this.name,
	profilePath = this.profilePath,
	id = this.id
)