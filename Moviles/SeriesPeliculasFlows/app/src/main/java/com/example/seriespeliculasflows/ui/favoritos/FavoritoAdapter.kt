package com.example.seriespeliculasflows.ui.favoritos

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.seriespeliculasflows.R
import com.example.seriespeliculasflows.data.remote.DataAccessResult
import com.example.seriespeliculasflows.databinding.FavoritoBinding
import com.example.seriespeliculasflows.ui.model.ItemUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import java.util.*

class FavoritoAdapter(
    val context: Context,
    val actions: FavoritoActions
) :
    ListAdapter<ItemUI, FavoritoAdapter.ItemViewholder>(DiffCallback()) {

    interface FavoritoActions {
        fun onDelete(item: ItemUI)
        fun onStartSelectMode()
        fun itemHasClicked(item: ItemUI)
        fun isItemSelected(item: ItemUI): Boolean
        fun detalles(item: ItemUI)

    }

    private var selectedMode: Boolean = false

    fun resetSelectMode() {
        selectedMode = false
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {

        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.favorito, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) = with(holder) {
        val item = getItem(position)
        bind(item)
    }

    inner class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = FavoritoBinding.bind(itemView)

        fun bind(item: ItemUI) {
            with(binding) {
                if (item is ItemUI.PeliculaUI) {
                    border.setBackgroundResource(R.drawable.border_pelicula)
                } else {
                    border.setBackgroundResource(R.drawable.border_serie)

                }
                poster.load(item.imagePath)
                name.text = item.name
                description.text = item.overview
                itemView.setOnClickListener {
                    actions.detalles(item)
                }

                itemView.setOnLongClickListener {
                    if (!selectedMode) {
                        selectedMode = true
                        actions.onStartSelectMode()
                        notifyDataSetChanged()
                    }
                    true
                }

                checkBox.setOnClickListener {
                    if (selectedMode) {
                        if (binding.checkBox.isChecked) {
                            itemView.setBackgroundColor(Color.GREEN)
                        } else {
                            itemView.setBackgroundColor(Color.WHITE)
                        }
                        actions.itemHasClicked(item)
                    }
                }

                if (selectedMode)
                    checkBox.visibility = View.VISIBLE
                else {
                    checkBox.visibility = View.GONE
                }

                if (actions.isItemSelected(item)) {
                    itemView.setBackgroundColor(Color.GREEN)
                    binding.checkBox.isChecked = true
                } else {
                    itemView.setBackgroundColor(Color.WHITE)
                    binding.checkBox.isChecked = false
                }

            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<ItemUI>() {
        override fun areItemsTheSame(oldItem: ItemUI, newItem: ItemUI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ItemUI, newItem: ItemUI): Boolean {
            return oldItem == newItem
        }
    }

    val swipeGesture = object : SwipeGesture(context) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            var initPos = viewHolder.adapterPosition
            var targetPos = target.adapterPosition

            val mutable = currentList.toMutableList()
            Collections.swap(mutable, initPos, targetPos)

            this@FavoritoAdapter.submitList(mutable)

            return false

        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (!selectedMode) {
                when (direction) {
                    ItemTouchHelper.RIGHT -> {
                        actions.onDelete(currentList[viewHolder.adapterPosition])
                    }
                }
            }
        }

    }
}

