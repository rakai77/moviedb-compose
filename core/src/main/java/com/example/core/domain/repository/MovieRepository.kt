package com.example.core.domain.repository

import com.example.core.data.BaseResult
import com.example.core.domain.model.AllTrending
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getAllTrending() : Flow<BaseResult<AllTrending>>
}