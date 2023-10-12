package com.example.playpal.home

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.playpal.core.data.Resource
import com.example.playpal.core.domain.model.Game
import com.example.playpal.core.ui.GameListAdapter
import com.example.playpal.core.ui.LoadingStateAdapter
import com.example.playpal.databinding.FragmentHomeListBinding
import com.example.playpal.detail.GameDetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.recyclerViewHome.layoutManager = GridLayoutManager(requireContext(), 2)
        } else {
            binding.recyclerViewHome.layoutManager = LinearLayoutManager(requireContext())
        }

        homeViewModel.game.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val gameAdapter = GameListAdapter(it.data!!)
                    with(binding.recyclerViewHome) {
                        layoutManager = LinearLayoutManager(context)
                        adapter = gameAdapter
                    }

                    gameAdapter.setOnItemClickCallback(object : GameListAdapter.OnItemClickCallback{
                        override fun onItemClicked(game: Game, holder: GameListAdapter.GameListViewHolder) {
                            Toast.makeText(context, "Item Clicked, ID : $id", Toast.LENGTH_SHORT).show()

                            val bundle = Bundle()
                            bundle.putParcelable(SELECTED_GAME_KEY, game)

                            val intent = Intent(activity, GameDetailActivity::class.java)
                            intent.putExtras(bundle)
                            startActivity(intent)
                        }
                    })
                }
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                else -> {}
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "HomeFragment"
        const val SELECTED_GAME_KEY = "selected_game_key"
    }

}