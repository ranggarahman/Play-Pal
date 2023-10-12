package com.example.playpal.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.playpal.R
import com.example.playpal.core.data.source.remote.response.ShortScreenshotsItem
import com.example.playpal.databinding.ItemScreenshotsBinding
import kotlinx.parcelize.RawValue

class GameScreenshotsAdapter(private val screenshots: @RawValue List<ShortScreenshotsItem>) : RecyclerView.Adapter<GameScreenshotsAdapter.ScreenshotsViewHolder>() {

    class ScreenshotsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemScreenshotsBinding.bind(itemView)

        val itemScreenshot = binding.screenshotsItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScreenshotsViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_screenshots, parent, false)
        return ScreenshotsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return screenshots.size
    }

    override fun onBindViewHolder(holder: ScreenshotsViewHolder, position: Int) {
        val screenshot = screenshots[position]

        Glide.with(holder.itemView.context)
            .load(screenshot.image)
            .into(holder.itemScreenshot)
    }
}