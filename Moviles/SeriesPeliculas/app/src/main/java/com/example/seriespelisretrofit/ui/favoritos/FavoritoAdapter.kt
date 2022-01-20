package com.example.seriespelisretrofit.ui.favoritos

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.seriespelisretrofit.R
import com.example.seriespelisretrofit.databinding.FavoritoBinding
import com.example.seriespelisretrofit.ui.model.FavoritoUI
import com.example.seriespelisretrofit.utils.Constants
import java.util.*

class FavoritoAdapter(
    val context: Context,
    val actions: FavoritoActions
) :
    ListAdapter<FavoritoUI, FavoritoAdapter.ItemViewholder>(DiffCallback()) {

    interface FavoritoActions {
        fun onDelete(favorito: FavoritoUI)
        fun onStartSelectMode()
        fun itemHasClicked(favorito: FavoritoUI)
        fun isItemSelected(favorito: FavoritoUI): Boolean
        fun detalles(favorito: FavoritoUI)

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

        fun bind(item: FavoritoUI) {
            with(binding) {
                if (item.tipo == Constants.PELICULA_TYPE) {
                    type.text = Constants.PELICULA_TYPE
                    type.setTextColor(ContextCompat.getColor(context, R.color.naranja_chungo))
                } else {
                    type.text = Constants.SERIE_TYPE
                    type.setTextColor(ContextCompat.getColor(context, R.color.morado_raruno))
                }
                poster.load(item.imagePath)
                name.text = item.name

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

    class DiffCallback : DiffUtil.ItemCallback<FavoritoUI>() {
        override fun areItemsTheSame(oldItem: FavoritoUI, newItem: FavoritoUI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FavoritoUI, newItem: FavoritoUI): Boolean {
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

