package com.example.seriespelisretrofit.ui.seriesActivity.detalles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.seriespelisretrofit.R
import com.example.seriespelisretrofit.databinding.SeasonBinding
import com.example.seriespelisretrofit.ui.model.TemporadaUI

class SeasonAdapter(val actions: SeasonAdapterActions) :
    ListAdapter<TemporadaUI, SeasonAdapter.SeasonViewHolder>(DiffCallback()) {

    interface SeasonAdapterActions {
        fun detalles(item: TemporadaUI)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonViewHolder {
        return SeasonViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.season, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SeasonViewHolder, position: Int) = with(holder) {
        val item = getItem(position)
        bind(item, position)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    inner class SeasonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = SeasonBinding.bind(view)
        fun bind(temporadaUI: TemporadaUI, position: Int) {
            with(binding) {
                seasonNumber.text = "Temporada ${position+1} capitulos: ${temporadaUI.episodeCount}"
            }
            itemView.setOnClickListener {
                actions.detalles(temporadaUI)
            }
        }

    }

    class DiffCallback : DiffUtil.ItemCallback<TemporadaUI>() {
        override fun areItemsTheSame(oldItem: TemporadaUI, newItem: TemporadaUI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TemporadaUI, newItem: TemporadaUI): Boolean {
            return oldItem == newItem
        }
    }
}