package com.example.core.domain.usecase

import com.example.core.data.BaseResult
import com.example.core.domain.model.SeriesDetail
import com.example.core.domain.repository.SeriesRepository
import kotlinx.coroutines.flow.Flow

class SeriesUseCase(
    private val repository: SeriesRepository,
) {

    suspend fun getSeriesDetail(seriesId: String) : Flow<BaseResult<SeriesDetail>> {
        return repository.getSeriesDetail(seriesId)
    }
}