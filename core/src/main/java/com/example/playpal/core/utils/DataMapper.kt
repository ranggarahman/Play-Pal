package com.example.playpal.core.utils

import com.example.playpal.core.data.source.local.entity.GameEntity
import com.example.playpal.core.data.source.remote.response.GameListResponse
import com.example.playpal.core.domain.model.Game

object DataMapper {

    fun mapGameResponsesToEntities(input: List<GameListResponse>): List<GameEntity> {
        val gameEntities = mutableListOf<GameEntity>()

        input.forEach { gameListResponse ->
            gameListResponse.results?.let { results ->
                gameEntities.addAll(results.map { gamesItemResponse ->
                    GameEntity(
                        id = gamesItemResponse?.id ?: 0,
                        added = gamesItemResponse?.added,
                        rating = gamesItemResponse?.rating.toString().toDouble(),
                        metacritic = gamesItemResponse?.metacritic,
                        playtime = gamesItemResponse?.playtime,
                        released = gamesItemResponse?.released,
                        slug = gamesItemResponse?.slug,
                        tba = gamesItemResponse?.tba,
                        name = gamesItemResponse?.name,
                        updated = gamesItemResponse?.updated,
                        reviewsCount = gamesItemResponse?.reviewsCount,
                        backgroundImage = gamesItemResponse?.backgroundImage,
                        isFavorite = false, // You can set this as needed
                        screenshots = gamesItemResponse?.shortScreenshots
                    )
                })
            }
        }

        return gameEntities
    }


    fun mapGameEntityToDomain(input: List<GameEntity>): List<Game> {
        return input.map { gameEntity ->
            Game(
                id = gameEntity.id,
                added = gameEntity.added,
                rating = gameEntity.rating,
                metacritic = gameEntity.metacritic,
                playtime = gameEntity.playtime,
                released = gameEntity.released,
                slug = gameEntity.slug,
                tba = gameEntity.tba,
                name = gameEntity.name,
                updated = gameEntity.updated,
                reviewsCount = gameEntity.reviewsCount,
                isFavorite = gameEntity.isFavorite,
                screenshots = gameEntity.screenshots,
                backgroundImage = gameEntity.backgroundImage
            )
        }
    }

    fun mapDomainToEntity(input: Game): GameEntity {
        return GameEntity(
            id = input.id,
            added = input.added,
            rating = input.rating,
            metacritic = input.metacritic,
            playtime = input.playtime,
            released = input.released,
            slug = input.slug,
            tba = input.tba,
            name = input.name,
            updated = input.updated,
            reviewsCount = input.reviewsCount,
            isFavorite = input.isFavorite,
            screenshots = input.screenshots,
            backgroundImage = input.backgroundImage
        )
    }


}