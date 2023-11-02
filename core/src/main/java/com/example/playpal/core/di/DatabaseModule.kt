package com.example.playpal.core.di

import android.content.Context
import androidx.room.Room
import com.example.playpal.core.data.source.local.room.GameDao
import com.example.playpal.core.data.source.local.room.GameDatabase
import com.example.playpal.core.data.source.local.room.RemoteKeysDao
import com.example.playpal.core.domain.model.Game
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): GameDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("My\$tr0ng_P@ssphr@se_2023".toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(
            context,
            GameDatabase::class.java,
            "Games.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    @Provides
    fun provideGameDao(database: GameDatabase): GameDao {
        return database.gameDao()
    }

    @Provides
    fun provideRemoteKeysDao(database: GameDatabase): RemoteKeysDao {
        return database.remoteKeysDao()
    }

}