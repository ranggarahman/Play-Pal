package com.example.playpal.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.playpal.R
import com.example.playpal.core.data.source.remote.response.ShortScreenshotsItem
import com.example.playpal.core.domain.model.Game
import com.example.playpal.databinding.ActivityGameDetailBinding
import com.example.playpal.home.HomeFragment.Companion.SELECTED_GAME_KEY
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.parcelize.RawValue

@AndroidEntryPoint
class GameDetailActivity : AppCompatActivity() {

    private val homeViewModel: GameDetailViewModel by viewModels()

    private lateinit var binding: ActivityGameDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGameDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val game = intent.getParcelableExtra<Game>(SELECTED_GAME_KEY)
        var favoriteStatus = game?.isFavorite
        favoriteStatus?.let {
            setFavoriteStatus(it)
        }
        binding.fabAddFavorite.setOnClickListener {
            if (game != null) {
                favoriteStatus = !favoriteStatus!!
                homeViewModel.setGameStatus(game, favoriteStatus!!)
                setFavoriteStatus(favoriteStatus!!)
            }
        }

        if (game != null) {
            binding.gameTitle.text = game.name
            Glide.with(this)
                .load(game.backgroundImage)
                .into(binding.gameImage)

            binding.gamePlaytime.text = getString(R.string.average_playtime, game.playtime.toString())
            binding.gameReleaseDate.text = getString(R.string.game_release_date, game.released)
            binding.gameMetacriticRating.text = getString(R.string.game_metacritic_rating, game.metacritic.toString())

            val screenshotsAdapter = GameScreenshotsAdapter(game.screenshots as @RawValue List<ShortScreenshotsItem>)

            with(binding.gameScreenshotsRecyclerView) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = screenshotsAdapter
            }
        }
    }

    private fun setFavoriteStatus(status : Boolean) {
        if (status) {
            binding.fabAddFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24))
        } else {
            binding.fabAddFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24))
        }
    }

}