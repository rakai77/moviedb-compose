package com.example.core.domain.model

data class Credits(
    val cast: List<CastingItem>,
    val id: Int,
    val crew: List<CrewItem>
)

data class CrewItem(
    val gender: Int,
    val creditId: String,
    val knownForDepartment: String,
    val originalName: String,
    val name: String,
    val profilePath: String,
    val id: Int,
    val department: String,
    val job: String
)

data class CastingItem(
    val castId: Int,
    val character: String,
    val gender: Int,
    val creditId: String,
    val knownForDepartment: String,
    val originalName: String,
    val name: String,
    val profilePath: String,
    val id: Int,
    val order: Int
)