package com.example.playpal.favorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.playpal.core.domain.model.Game
import com.example.playpal.core.ui.GameListAdapter
import com.example.playpal.detail.GameDetailActivity
import com.example.playpal.di.FavoriteModuleDependencies
import com.example.playpal.favorite.databinding.FragmentFavoriteGamesBinding
import com.example.playpal.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteGamesFragment : Fragment() {
    @Inject
    lateinit var factory: ViewModelFactory

    private var _binding: FragmentFavoriteGamesBinding? = null
    private val binding get() = _binding!!

    private val favoriteGamesViewModel : FavoriteGamesViewModel by viewModels {
        factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        context?.let {
            EntryPointAccessors.fromApplication(
                it.applicationContext,
                FavoriteModuleDependencies::class.java
            )
        }?.let {
            DaggerFavoriteGamesComponent.builder()
                .context(requireContext())
                .appDependencies(
                    it
                )
                .build()
                .inject(this)
        }

        _binding = FragmentFavoriteGamesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoriteGamesViewModel.favoriteGames.observe(viewLifecycleOwner){favoriteGames ->
            val favoriteGamesAdapter = GameListAdapter(favoriteGames)

            with(binding.recyclerViewFavorite) {
                layoutManager = LinearLayoutManager(context)
                adapter = favoriteGamesAdapter
            }

            favoriteGamesAdapter.setOnItemClickCallback(object : GameListAdapter.OnItemClickCallback{
                override fun onItemClicked(game: Game, holder: GameListAdapter.GameListViewHolder) {
                    Toast.makeText(context, "Item Clicked, ID : $id", Toast.LENGTH_SHORT).show()

                    val bundle = Bundle()
                    bundle.putParcelable(HomeFragment.SELECTED_GAME_KEY, game)

                    val intent = Intent(activity, GameDetailActivity::class.java)
                    intent.putExtras(bundle)
                    startActivity(intent)
                }
            })
        }
    }

}