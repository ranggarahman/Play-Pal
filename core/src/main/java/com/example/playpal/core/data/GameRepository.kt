package com.example.playpal.core.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.playpal.core.data.source.local.LocalDataSource
import com.example.playpal.core.data.source.local.entity.GameEntity
import com.example.playpal.core.data.source.local.entity.RemoteKeys
import com.example.playpal.core.data.source.remote.RemoteDataSource
import com.example.playpal.core.data.source.remote.network.ApiResponse
import com.example.playpal.core.data.source.remote.response.GameListResponse
import com.example.playpal.core.domain.model.Game
import com.example.playpal.core.domain.repository.IGameRepository
import com.example.playpal.core.utils.AppExecutors
import com.example.playpal.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IGameRepository {
    override fun getAllGame(page: Int, pageSize: Int): Flow<Resource<List<Game>>> {
        return object : NetworkBoundResource<List<Game>, List<GameListResponse>>() {
            override fun loadFromDB(): Flow<List<Game>> {
                return localDataSource.getAllGame().map {
                    DataMapper.mapGameEntityToDomain(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<GameListResponse>>> {
                val response = remoteDataSource.getAllGames(page, pageSize)
                Log.d(TAG1, "response : ${response.first()}")
                return response
            }

            override suspend fun saveCallResult(data: List<GameListResponse>) {
                val gameEntity = DataMapper.mapGameResponsesToEntities(data)
                localDataSource.insertGame(gameEntity)
                Log.d(TAG1, "SAVE CALL INSERTION CALLED : GAME ENTITY : $gameEntity")
            }

            override fun shouldFetch(data: List<Game>?): Boolean {
                return data.isNullOrEmpty()
            }

        }.asFlow()
    }
//
//    override fun getAllGameAsPagingSource(): PagingSource<Int, Game> {
//        return localDataSource.getAllGameAsPagingSource()
//    }

//    override suspend fun insertGame(data: List<Game>) {
//        val gameEntities = data.map { DataMapper.mapDomainToEntity(it) }
//        localDataSource.insertGame(gameEntities)
//
//    }


    override fun getFavoriteGame(): Flow<List<Game>> {
        return localDataSource.getFavoriteGame().map {
            DataMapper.mapGameEntityToDomain(it)
        }
    }

    override fun setFavoriteGame(game: Game, state: Boolean) {
        val gameEntity = DataMapper.mapDomainToEntity(game)
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteGame(
                gameEntity,
                state
            )
        }
    }

//    override fun deleteRemoteKeys() {
//        appExecutors.diskIO().execute {
//            localDataSource.deleteRemoteKeys()
//        }
//    }
//
//    override fun insertAllRemoteKeys(keys: List<RemoteKeys>) {
//        appExecutors.diskIO().execute {
//            localDataSource.insertAllRemoteKeys(keys)
//        }
//    }
//
//    override fun getRemoteKeyForLastItem(state: PagingState<Int, Game>)
//    : RemoteKeys? = localDataSource.getRemoteKeyForLastItem(state)
//
//    override fun getRemoteKeyForFirstItem(state: PagingState<Int, Game>)
//    : RemoteKeys? = localDataSource.getRemoteKeyForFirstItem(state)
//
//    override fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Game>)
//    : RemoteKeys? = localDataSource.getRemoteKeyClosestToCurrentPosition(state)

    private companion object {
        private const val TAG1 = "GameRepository"
    }
}