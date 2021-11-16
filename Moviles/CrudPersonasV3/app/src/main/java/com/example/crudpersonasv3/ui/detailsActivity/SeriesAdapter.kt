package com.example.crudpersonasv3.ui.detailsActivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.crudpersonasv3.R
import com.example.crudpersonasv3.databinding.SerieBinding
import com.example.crudpersonasv3.ui.domain.SerieUI

class SeriesAdapter(
    private val delete: (SerieUI) -> Unit
) : ListAdapter<SerieUI, SeriesAdapter.SerieViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SerieViewHolder {
        return SerieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.serie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SerieViewHolder, position: Int) =
        with(holder) {
            val item = getItem(position)
            bind(item, delete)
        }

    override fun getItemCount(): Int {
        return currentList.size
    }

    class SerieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = SerieBinding.bind(view)

        fun bind(
            comic: SerieUI,
            delete: (SerieUI) -> Unit
        ) {
            with(binding) {
                serieName.text = comic.name
                buttonDelete.setOnClickListener {
                    delete(comic)
                }
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