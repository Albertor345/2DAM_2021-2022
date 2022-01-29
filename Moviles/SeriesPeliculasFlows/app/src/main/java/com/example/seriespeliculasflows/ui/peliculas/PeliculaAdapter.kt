package com.example.seriespeliculasflows.ui.peliculas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.seriespeliculasflows.R
import com.example.seriespeliculasflows.databinding.PeliculaBinding
import com.example.seriespeliculasflows.ui.model.PeliculaUI

class PeliculaAdapter(val actions: PeliculaAdapterActions) :
    ListAdapter<PeliculaUI, PeliculaAdapter.PeliculaViewHolder>(DiffCallback()) {

    interface PeliculaAdapterActions {
        fun detalles(item: PeliculaUI)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculaViewHolder {
        return PeliculaViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.pelicula, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PeliculaViewHolder, position: Int) = with(holder) {
        val item = getItem(position)
        bind(item)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    inner class PeliculaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = PeliculaBinding.bind(view)
        fun bind(peliculaUI: PeliculaUI) {
            with(binding) {
                title.text = peliculaUI.title
                image.load(peliculaUI.posterPath)
            }
            itemView.setOnClickListener {
                actions.detalles(peliculaUI)
            }
        }

    }

    class DiffCallback : DiffUtil.ItemCallback<PeliculaUI>() {
        override fun areItemsTheSame(oldItem: PeliculaUI, newItem: PeliculaUI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PeliculaUI, newItem: PeliculaUI): Boolean {
            return oldItem == newItem
        }
    }
}