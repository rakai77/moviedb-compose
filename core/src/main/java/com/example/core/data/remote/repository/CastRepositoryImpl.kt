package com.example.core.data.remote.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core.data.remote.response.toDomain
import com.example.core.data.remote.service.Service
import com.example.core.domain.model.CastItem
import com.example.core.domain.repository.CastRepository
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.utils.io.errors.IOException
import java.net.UnknownHostException

class CastRepositoryImpl(
    private val service: Service
) : CastRepository {
    override fun getPopularCast(): PagingSource<Int, CastItem> {
        return object : PagingSource<Int, CastItem>() {
            override fun getRefreshKey(state: PagingState<Int, CastItem>): Int? {
                return state.anchorPosition
            }

            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CastItem> {
                return try {
                    val castPage = params.key ?: 1
                    val response = service.getPopularCast(castPage)
                    val castData = response.toDomain().results

                    LoadResult.Page(
                        prevKey = null,
                        nextKey = if (castData.isEmpty()) null else castPage + 1,
                        data = castData
                    )
                } catch (e: Throwable) {
                    LoadResult.Error(e)
                } catch (e: ServerResponseException) {
                    LoadResult.Error(e)
                } catch (e: IOException) {
                    LoadResult.Error(e)
                } catch (e: SocketTimeoutException) {
                    LoadResult.Error(e)
                } catch (e: UnknownHostException) {
                    LoadResult.Error(e)
                }
            }
        }
    }

    override fun getCastFromSearch(query: String): PagingSource<Int, CastItem> {
        return object : PagingSource<Int, CastItem>() {
            override fun getRefreshKey(state: PagingState<Int, CastItem>): Int? {
                return state.anchorPosition
            }

            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CastItem> {
                return try {
                    val castPage = params.key ?: 1
                    val response = service.getCastFromSearch(query, castPage)
                    val castData = response.toDomain().results

                    LoadResult.Page(
                        prevKey = null,
                        nextKey = if (castData.isEmpty()) null else castPage + 1,
                        data = castData
                    )
                } catch (e: Throwable) {
                    LoadResult.Error(e)
                } catch (e: ServerResponseException) {
                    LoadResult.Error(e)
                } catch (e: IOException) {
                    LoadResult.Error(e)
                } catch (e: SocketTimeoutException) {
                    LoadResult.Error(e)
                } catch (e: UnknownHostException) {
                    LoadResult.Error(e)
                }
            }
        }
    }
}