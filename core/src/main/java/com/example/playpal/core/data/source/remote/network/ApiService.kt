package com.example.playpal.core.data.source.remote.network

import com.example.playpal.core.data.source.remote.response.GameListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("games")
    suspend fun getList(
        @Query("page") page: Int? = null,
        @Query("page_size") pageSize: Int? = null,
        @Query("key") apiKey: String = "8731cc8f99374caaa206a29de65dcecd"
    ): GameListResponse
}