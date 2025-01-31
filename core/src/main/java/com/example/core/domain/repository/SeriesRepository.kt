package com.example.core.domain.repository

import com.example.core.data.BaseResult
import com.example.core.domain.model.SeriesDetail
import kotlinx.coroutines.flow.Flow

interface SeriesRepository {

    suspend fun getSeriesDetail(seriesId: String) : Flow<BaseResult<SeriesDetail>>
}