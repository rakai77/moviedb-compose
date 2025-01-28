package com.example.core.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.domain.model.CastItem
import com.example.core.domain.repository.CastRepository
import kotlinx.coroutines.flow.Flow

class CastUseCase(
    private val castRepository: CastRepository,
) {

    fun searchCast(query: String) : Flow<PagingData<CastItem>> {
        return Pager(
            config = PagingConfig(
                initialLoadSize = 20,
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { castRepository.getCast(query) }
        ).flow
    }
}