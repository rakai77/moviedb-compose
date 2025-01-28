package com.example.core.domain.model

data class Cast(
    val page: Int,
    val totalPages: Int,
    val results: List<CastItem>,
    val totalResults: Int
)

data class CastItem(
    val originalName: String? = null,
    val name: String? = null,
    val profilePath: String? = null,
    val id: Int? = null,
)