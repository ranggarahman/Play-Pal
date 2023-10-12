package com.example.playpal.core.data.source.local

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.playpal.core.data.source.local.entity.GameEntity
import com.example.playpal.core.data.source.local.entity.RemoteKeys
import com.example.playpal.core.data.source.local.room.GameDao
import com.example.playpal.core.data.source.local.room.RemoteKeysDao
import com.example.playpal.core.domain.model.Game
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val gameDao: GameDao,
    private val remoteKeysDao: RemoteKeysDao
) {

    fun getAllGame(): Flow<List<GameEntity>> = gameDao.getAllGames()
    fun getAllGameAsPagingSource(): PagingSource<Int, Game> = gameDao.getAllGamesAsPagingSource()

    fun getFavoriteGame(): Flow<List<GameEntity>> = gameDao.getFavoriteGames()

    suspend fun insertGame(gameList: List<GameEntity>) = gameDao.insertGames(gameList)

    fun setFavoriteGame(game: GameEntity, newState: Boolean) {
        game.isFavorite = newState
        gameDao.updateFavoriteGames(game)
    }

    fun deleteRemoteKeys() {
        remoteKeysDao.deleteRemoteKeys()
    }

    fun insertAllRemoteKeys(keys : List<RemoteKeys>) {
        remoteKeysDao.insertAll(keys)
    }


    fun getRemoteKeyForLastItem(state: PagingState<Int, Game>): RemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { data ->
            data.id.let { remoteKeysDao.getRemoteKeysId(it.toString()) }
        }
    }

    fun getRemoteKeyForFirstItem(state: PagingState<Int, Game>): RemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { data ->
            data.id.let { remoteKeysDao.getRemoteKeysId(it.toString()) }
        }
    }

    fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Game>): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                remoteKeysDao.getRemoteKeysId(id.toString())
            }
        }
    }

}