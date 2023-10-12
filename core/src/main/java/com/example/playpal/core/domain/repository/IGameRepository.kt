package com.example.playpal.core.domain.repository

import com.example.playpal.core.data.Resource
import com.example.playpal.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface IGameRepository {

    fun getAllGame(page: Int, pageSize: Int): Flow<Resource<List<Game>>>

//    fun getAllGameAsPagingSource(): PagingSource<Int, Game>

    //suspend fun insertGame(data : List<Game>)

    fun getFavoriteGame(): Flow<List<Game>>

    fun setFavoriteGame(game: Game, state: Boolean)

//    fun deleteRemoteKeys()
//
//    fun insertAllRemoteKeys(keys : List<RemoteKeys>)
//
//    fun getRemoteKeyForLastItem(state: PagingState<Int, Game>): RemoteKeys?
//
//    fun getRemoteKeyForFirstItem(state: PagingState<Int, Game>): RemoteKeys?
//
//    fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Game>): RemoteKeys?
}