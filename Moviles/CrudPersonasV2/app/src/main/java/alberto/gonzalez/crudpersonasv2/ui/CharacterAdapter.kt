package alberto.gonzalez.crudpersonasv2.ui

import alberto.gonzalez.crudpersonasv2.R
import alberto.gonzalez.crudpersonasv2.databinding.CharacterBinding
import alberto.gonzalez.crudpersonasv2.domain.Character
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import coil.load
import java.time.LocalDate
import java.util.stream.Collectors

class CharacterAdapter(
    private var characters: MutableList<Character>,
    private val delete: (Int) -> Unit,
    private val details: (Int, View, View) -> Unit
) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>(), Filterable {
    val originalData = characters

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CharacterViewHolder(layoutInflater.inflate(R.layout.character, parent, false))
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.load(characters[position], delete, details)
    }

    override fun getItemCount(): Int {
        return characters.size
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
                        characters.stream().filter {
                            it.name.lowercase().startsWith(searchStr)
                        }.collect(Collectors.toList())
                    } else {
                        searchStr.let {
                            it.split("/")[1].let {
                                characters.stream().filter { char ->
                                    LocalDate.parse(char.modified).isBefore(LocalDate.parse (it))
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
                characters = results.values as MutableList<Character>
                notifyDataSetChanged()
            }
        }
    }

    class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = CharacterBinding.bind(view)
        fun load(character: Character, delete: (Int) -> Unit, details: (Int, View, View) -> Unit) {
            with(binding) {
                textViewNombreCharacter.text = character.name
                imageViewThumbnail.load(character.image.path + "." + character.image.extension)
                buttonDelete.setOnClickListener {
                    delete(adapterPosition)
                }
                buttonDetails.setOnClickListener {
                    details(adapterPosition, imageViewThumbnail, textViewNombreCharacter)
                }
            }
        }
    }


}