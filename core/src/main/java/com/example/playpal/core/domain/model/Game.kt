package com.example.playpal.core.domain.model

import android.os.Parcelable
import com.example.playpal.core.data.source.remote.response.ShortScreenshotsItem
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Game(
    val id: Int,
    val added: Int?,
    val rating: Double?,
    val metacritic: Int?,
    val playtime: Int?,
    val released: String?,
    val slug: String?,
    val tba: Boolean?,
    val name: String?,
    val backgroundImage: String?,
    val updated: String?,
    val reviewsCount: Int?,
    var isFavorite: Boolean = false,
    val screenshots: @RawValue List<ShortScreenshotsItem?>?
) : Parcelable
