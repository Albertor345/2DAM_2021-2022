package com.example.seriespeliculasflows.ui.seasons

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.seriespeliculasflows.R
import com.example.seriespeliculasflows.databinding.CapituloBinding
import com.example.seriespeliculasflows.ui.model.CapituloUI

class ChapterAdapter() :
    ListAdapter<CapituloUI, ChapterAdapter.ChapterViewHolder>(
        DiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterViewHolder {
        return ChapterViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.capitulo, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ChapterViewHolder, position: Int) = with(holder) {
        val item = getItem(position)
        bind(item, position)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    inner class ChapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = CapituloBinding.bind(view)
        fun bind(capituloUI: CapituloUI, position: Int) {
            with(binding) {
                titulo.text = "${capituloUI.name} ${capituloUI.episodeNumber}"
            }
        }

    }

    class DiffCallback : DiffUtil.ItemCallback<CapituloUI>() {
        override fun areItemsTheSame(oldItem: CapituloUI, newItem: CapituloUI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CapituloUI, newItem: CapituloUI): Boolean {
            return oldItem == newItem
        }
    }

}