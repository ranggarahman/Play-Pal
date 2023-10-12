package com.example.playpal.core.domain.usecase

import com.example.playpal.core.data.Resource
import com.example.playpal.core.domain.model.Game
import com.example.playpal.core.domain.repository.IGameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameInteractor @Inject constructor(
    private val gameRepository: IGameRepository
) : GameUseCase {

    override fun getAllGame(): Flow<Resource<List<Game>>> {
        return gameRepository.getAllGame(1, 30)
    }

    override fun getFavoriteGame(): Flow<List<Game>> {
        return gameRepository.getFavoriteGame()
    }

    override fun setFavoriteGame(game: Game, state: Boolean) {
        gameRepository.setFavoriteGame(game, state)
    }
}