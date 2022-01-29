package com.example.seriespeliculasflows.ui.series

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.seriespeliculasflows.R
import com.example.seriespeliculasflows.databinding.SerieBinding
import com.example.seriespeliculasflows.ui.model.SerieUI

class SerieAdapter(val actions: SerieAdapterActions) :
    ListAdapter<SerieUI, SerieAdapter.SerieViewHolder>(DiffCallback()) {

    interface SerieAdapterActions {
        fun detalles(item: SerieUI)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SerieViewHolder {
        return SerieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.serie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SerieViewHolder, position: Int) = with(holder) {
        val item = getItem(position)
        bind(item)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    inner class SerieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = SerieBinding.bind(view)
        fun bind(serieUI: SerieUI) {
            with(binding) {
                title.text = serieUI.name
                image.load(serieUI.posterPath)
            }
            itemView.setOnClickListener {
                actions.detalles(serieUI)
            }
        }

    }

    class DiffCallback : DiffUtil.ItemCallback<SerieUI>() {
        override fun areItemsTheSame(oldItem: SerieUI, newItem: SerieUI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SerieUI, newItem: SerieUI): Boolean {
            return oldItem == newItem
        }
    }
}