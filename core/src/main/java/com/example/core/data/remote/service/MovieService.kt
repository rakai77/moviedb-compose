package com.example.core.data.remote.service

import com.example.core.data.remote.response.AllTrendingResponse

interface MovieService {

    suspend fun getAllTrending() : AllTrendingResponse

}