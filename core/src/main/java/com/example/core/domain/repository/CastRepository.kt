package com.example.core.domain.repository

import androidx.paging.PagingSource
import com.example.core.domain.model.CastItem

interface CastRepository {

    fun getCast(query: String) : PagingSource<Int, CastItem>
}