package com.example.playpal.core.data.source.local.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.playpal.core.data.source.local.entity.GameEntity
import com.example.playpal.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Transaction
    @Query("SELECT * FROM games")
    fun getAllGames(): Flow<List<GameEntity>>

    @Query("SELECT * FROM games")
    fun getAllGamesAsPagingSource(): PagingSource<Int, Game>

    @Query("SELECT * FROM games where isFavorite = 1")
    fun getFavoriteGames(): Flow<List<GameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGames(game: List<GameEntity>)

    @Update
    fun updateFavoriteGames(game: GameEntity)

}