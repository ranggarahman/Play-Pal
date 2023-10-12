package com.example.playpal.core.data.source.remote

import android.util.Log
import com.example.playpal.core.data.source.remote.network.ApiResponse
import com.example.playpal.core.data.source.remote.network.ApiService
import com.example.playpal.core.data.source.remote.response.GameListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllGames(page: Int, pageSize: Int): Flow<ApiResponse<List<GameListResponse>>> {
        return flow {
            try {
                val response = apiService.getList(
                    page = page,
                    pageSize = pageSize
                )
                val dataArray = mutableListOf<GameListResponse>()
                dataArray.add(response)
                Log.e(TAG, "RES: ${dataArray.size}")
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    companion object {
        private const val TAG = "RemoteDataSource"
    }
}