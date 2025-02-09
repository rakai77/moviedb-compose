package com.example.core.data.remote.response

import com.example.core.domain.model.CastingItem
import com.example.core.domain.model.Credits
import com.example.core.domain.model.CrewItem
import com.google.gson.annotations.SerializedName

data class CreditsResponse(

	@field:SerializedName("cast")
	val cast: List<CastingItemResponse>,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("crew")
	val crew: List<CrewItemResponse>
)

data class CrewItemResponse(

	@field:SerializedName("gender")
	val gender: Int? = null,

	@field:SerializedName("credit_id")
	val creditId: String? = null,

	@field:SerializedName("known_for_department")
	val knownForDepartment: String? = null,

	@field:SerializedName("original_name")
	val originalName: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("profile_path")
	val profilePath: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("department")
	val department: String? = null,

	@field:SerializedName("job")
	val job: String? = null
)

data class CastingItemResponse(

	@field:SerializedName("cast_id")
	val castId: Int? = null,

	@field:SerializedName("character")
	val character: String? = null,

	@field:SerializedName("gender")
	val gender: Int? = null,

	@field:SerializedName("credit_id")
	val creditId: String? = null,

	@field:SerializedName("known_for_department")
	val knownForDepartment: String? = null,

	@field:SerializedName("original_name")
	val originalName: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("profile_path")
	val profilePath: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("order")
	val order: Int? = null
)

fun CreditsResponse.toDomain() = Credits(
	id = this.id,
	cast = this.cast.map { it.toDomain() },
	crew = this.crew.map { it.toDomain() }
)

fun CastingItemResponse.toDomain() = CastingItem(
	castId = this.castId ?: 0,
	character = this.character ?: "",
	gender = this.gender ?: 0,
	creditId = this.creditId ?: "",
	knownForDepartment = this.knownForDepartment ?: "",
	originalName = this.originalName ?: "",
	name = this.name ?: "",
	profilePath = this.profilePath ?: "",
	id = this.id ?: 0,
	order = this.order ?: 0
)

fun CrewItemResponse.toDomain() = CrewItem(
	gender = this.gender ?: 0,
	creditId = this.creditId ?: "",
	knownForDepartment = this.knownForDepartment ?: "",
	originalName = this.originalName ?: "",
	name = this.name ?: "",
	profilePath = this.profilePath ?: "",
	id = this.id ?: 0,
	department = this.department ?: "",
	job = this.job ?: ""
)