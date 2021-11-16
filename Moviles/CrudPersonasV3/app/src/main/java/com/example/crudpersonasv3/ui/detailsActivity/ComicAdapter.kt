package com.example.crudpersonasv3.ui.detailsActivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.crudpersonasv3.R
import com.example.crudpersonasv3.databinding.ComicBinding
import com.example.crudpersonasv3.ui.domain.ComicUI

class ComicAdapter(
    private val delete: (ComicUI) -> Unit
) : ListAdapter<ComicUI, ComicAdapter.ComicViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ComicViewHolder {
        return ComicViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.comic, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) =
        with(holder) {
            val item = getItem(position)
            bind(item, delete)
        }

    override fun getItemCount(): Int {
        return currentList.size
    }

    class ComicViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ComicBinding.bind(view)

        fun bind(
            comic: ComicUI,
            delete: (ComicUI) -> Unit
        ) {
            with(binding) {
                comicName.text = comic.name
                buttonDelete.setOnClickListener {
                    delete(comic)
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<ComicUI>() {
        override fun areItemsTheSame(oldItem: ComicUI, newItem: ComicUI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ComicUI, newItem: ComicUI): Boolean {
            return oldItem == newItem
        }
    }
}