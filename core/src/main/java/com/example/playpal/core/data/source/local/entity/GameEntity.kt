package com.example.playpal.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import androidx.room.TypeConverters
import com.example.playpal.core.data.source.remote.response.EsrbRating
import com.example.playpal.core.data.source.remote.response.GenresItem
import com.example.playpal.core.data.source.remote.response.ParentPlatformsItem
import com.example.playpal.core.data.source.remote.response.PlatformsItem
import com.example.playpal.core.data.source.remote.response.RatingsItem
import com.example.playpal.core.data.source.remote.response.ShortScreenshotsItem
import com.example.playpal.core.data.source.remote.response.StoresItem
import com.example.playpal.core.data.source.remote.response.TagsItem
import com.example.playpal.core.utils.Converters

@Entity(tableName = "games")
data class GameEntity(
    @PrimaryKey
    val id: Int,
    val added: Int?,
    val rating: Double?,
    val metacritic: Int?,
    val playtime: Int?,
    val released: String?,
    val slug: String?,
    val tba: Boolean?,
    val name: String?,
    val updated: String?,
    val reviewsCount: Int?,
    val backgroundImage: String?,
    var isFavorite: Boolean = false ,
    // ... other fields from GamesItemResponse

    @TypeConverters(Converters::class)
    @ColumnInfo(name = "screenshots")
    val screenshots: List<ShortScreenshotsItem?>?
)



