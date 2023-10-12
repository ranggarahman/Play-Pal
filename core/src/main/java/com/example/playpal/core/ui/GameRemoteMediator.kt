package com.example.playpal.core.ui

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.playpal.core.data.Resource
import com.example.playpal.core.data.source.local.entity.RemoteKeys
import com.example.playpal.core.domain.model.Game
import com.example.playpal.core.domain.repository.IGameRepository
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

//@OptIn(ExperimentalPagingApi::class)
//class GameRemoteMediator(
//    private val gameRepository: IGameRepository
//) : RemoteMediator<Int, Game>() {

//    override suspend fun initialize(): InitializeAction {
//        return InitializeAction.LAUNCH_INITIAL_REFRESH
//    }
//
//    override suspend fun load(
//        loadType: LoadType,
//        state: PagingState<Int, Game>
//    ): MediatorResult {
//        val page = when (loadType) {
//            LoadType.REFRESH -> {
//                val remoteKeys = gameRepository.getRemoteKeyClosestToCurrentPosition(state)
//                remoteKeys?.nextKey?.minus(1) ?: INITIAL_PAGE_INDEX
//            }
//            LoadType.PREPEND -> {
//                val remoteKeys = gameRepository.getRemoteKeyForFirstItem(state)
//                val prevKey = remoteKeys?.prevKey
//                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
//                prevKey
//            }
//            LoadType.APPEND -> {
//                val remoteKeys = gameRepository.getRemoteKeyForLastItem(state)
//                val nextKey = remoteKeys?.nextKey
//                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
//                nextKey
//            }
//        }
//
//        return try {
//            val responseData = gameRepository.getAllGame(page, state.config.pageSize)
//            val gameList: List<Game> = responseData
//                .filterIsInstance<Resource.Success<List<Game>>>()
//                .map { it.data ?: emptyList() }
//                .firstOrNull() ?: emptyList()
//
//            Log.d(TAG, "RESULT : ${gameList[1]}, ${gameList.size}")
//
//            val endOfPaginationReached = gameList.isEmpty()
//
//            if (loadType == LoadType.REFRESH) {
//                gameRepository.deleteRemoteKeys()
//            }
//
//            val prevKey = if (page == 1) null else page - 1
//            val nextKey = if (endOfPaginationReached) null else page + 1
//            val keys = gameList.map {
//                RemoteKeys(id = it.id.toString(), prevKey = prevKey, nextKey = nextKey)
//            }
//
//            gameRepository.insertAllRemoteKeys(keys)
//            gameRepository.insertGame(gameList)
//
//            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
//        } catch (exception : Exception) {
//            MediatorResult.Error(exception)
//        }
//    }
//
//    private companion object {
//        const val INITIAL_PAGE_INDEX = 1
//        private const val TAG = "GameRemoteMediator"
//    }
//}
