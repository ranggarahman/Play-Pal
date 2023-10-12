package com.example.playpal.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.playpal.core.R
import com.example.playpal.core.databinding.ItemGameBinding
import com.example.playpal.core.domain.model.Game

class GameListAdapter (private val gameList: List<Game>)
    : RecyclerView.Adapter<GameListAdapter.GameListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    class GameListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemGameBinding.bind(itemView)

        val gameImage = binding.gameItemImage
        val gameTitle = binding.gameItemTitle
    }

    override fun onBindViewHolder(holder: GameListViewHolder, position: Int) {
        val game = gameList[position]
        holder.gameTitle.text = game.name
        Glide.with(holder.itemView.context)
            .load(game.backgroundImage)
            .into(holder.gameImage)

        holder.itemView.setOnClickListener {
            game.let {
                onItemClickCallback.onItemClicked(it, holder)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameListViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        return GameListViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return gameList.size
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback{
        fun onItemClicked(game: Game, holder: GameListViewHolder)
    }

    companion object {

    }
}