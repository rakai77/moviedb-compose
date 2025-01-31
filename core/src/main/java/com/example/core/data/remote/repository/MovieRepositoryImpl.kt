package com.example.core.data.remote.repository

import com.example.core.data.BaseResult
import com.example.core.data.remote.response.toDomain
import com.example.core.data.remote.service.Service
import com.example.core.domain.model.MovieDetail
import com.example.core.domain.repository.MovieRepository
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.net.UnknownHostException

class MovieRepositoryImpl(
    private val apiService: Service
) : MovieRepository {
    override suspend fun getMovieDetails(movieId: String): Flow<BaseResult<MovieDetail>> {
        return flow {
            try {
                val response = apiService.getMovieDetail(movieId)
                emit(BaseResult.Success(response.toDomain()))
            } catch (t: Throwable) {
                when (t) {
                    is ClientRequestException -> {
                        emit(BaseResult.Error(t.message, t.response.status.value))
                    }
                    is ServerResponseException -> {
                        emit(BaseResult.Error(t.message,t.response.status.value))
                    }
                    is UnknownHostException ->  {
                        emit(BaseResult.Error("Check your internet connection", null))
                    }
                    is SocketTimeoutException -> {
                        emit(BaseResult.Error(t.message ?: "Timeout",null))
                    }
                    else -> emit(BaseResult.Error(t.message ?: "Something went wrong", null))
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}