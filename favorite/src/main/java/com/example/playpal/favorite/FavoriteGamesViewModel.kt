package com.example.playpal.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.playpal.core.domain.usecase.GameUseCase

class FavoriteGamesViewModel (gameUseCase: GameUseCase): ViewModel() {
    val favoriteGames = gameUseCase.getFavoriteGame().asLiveData()
}