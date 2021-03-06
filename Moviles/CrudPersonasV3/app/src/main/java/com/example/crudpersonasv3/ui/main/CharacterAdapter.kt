package com.example.crudpersonasv3.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.crudpersonasv3.R
import com.example.crudpersonasv3.databinding.CharacterBinding
import com.example.crudpersonasv3.ui.domain.CharacterUI
import java.time.LocalDate
import java.util.stream.Collectors

class CharacterAdapter(
    private val delete: (CharacterUI) -> Unit,
    private val details: (CharacterUI, View, View) -> Unit,
    private val update: (CharacterUI) -> Unit
) : ListAdapter<CharacterUI, CharacterAdapter.CharacterViewHolder>(DiffCallback()), Filterable {
    val originalData: MutableList<CharacterUI> = currentList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.character, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) = with(holder) {
        val item = getItem(position)
        bind(item, delete, details, update)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {

            override fun performFiltering(constraint: CharSequence): FilterResults {
                val filterResults = FilterResults()
                if (constraint.isNullOrEmpty() || constraint.isNullOrBlank()) {
                    filterResults.count = originalData.size
                    filterResults.values = originalData
                } else {
                    val searchStr = constraint.toString().lowercase()
                    var results = if (!searchStr.contains(R.string.dateFiltering.toString())) {
                        currentList.stream().filter {
                            it.name.lowercase().startsWith(searchStr)
                        }.collect(Collectors.toList())
                    } else {
                        searchStr.let {
                            it.split("/")[1].let {
                                currentList.stream().filter { char ->
                                    LocalDate.parse(char.modified).isBefore(LocalDate.parse(it))
                                }.collect(Collectors.toList())
                            }
                        }
                    }
                    filterResults.count = results.size
                    filterResults.values = results
                }
                return filterResults
            }

            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                submitList(results.values as List<CharacterUI>)
            }
        }
    }

    class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = CharacterBinding.bind(view)

        fun bind(
            character: CharacterUI,
            delete: (CharacterUI) -> Unit,
            details: (CharacterUI, View, View) -> Unit,
            update: (CharacterUI) -> Unit
        ) {
            with(binding) {
                textViewNombreCharacter.text = character.name
                imageViewThumbnail.load(character.image)
                buttonDelete.setOnClickListener {
                    delete(character)
                }
                buttonDetails.setOnClickListener {
                    details(character, imageViewThumbnail, textViewNombreCharacter)
                }
                buttonUpdate.setOnClickListener {
                    update(character)
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<CharacterUI>() {
        override fun areItemsTheSame(oldItem: CharacterUI, newItem: CharacterUI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CharacterUI, newItem: CharacterUI): Boolean {
            return oldItem == newItem
        }
    }
}