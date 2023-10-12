package com.example.playpal.detail

import androidx.lifecycle.ViewModel
import com.example.playpal.core.domain.model.Game
import com.example.playpal.core.domain.usecase.GameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameDetailViewModel @Inject constructor(private val gameUseCase: GameUseCase): ViewModel() {
    fun setGameStatus(game: Game, newStatus: Boolean) {
        gameUseCase.setFavoriteGame(game, newStatus)
    }
}