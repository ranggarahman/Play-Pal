package com.example.playpal.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.playpal.core.data.source.local.entity.GameEntity
import com.example.playpal.core.data.source.local.entity.RemoteKeys
import com.example.playpal.core.utils.Converters

@Database(
    entities = [GameEntity::class, RemoteKeys::class],
    version = 2,
    exportSchema = false)
@TypeConverters(Converters::class)
abstract class GameDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDao

    abstract fun remoteKeysDao(): RemoteKeysDao

}