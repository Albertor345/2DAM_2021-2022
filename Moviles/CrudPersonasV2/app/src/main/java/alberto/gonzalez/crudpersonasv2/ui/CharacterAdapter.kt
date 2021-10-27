package alberto.gonzalez.crudpersonasv2.ui

import alberto.gonzalez.crudpersonasv2.R
import alberto.gonzalez.crudpersonasv2.databinding.CharacterBinding
import alberto.gonzalez.crudpersonasv2.domain.Character
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load

class CharacterAdapter(
    private val characters: MutableList<Character>,
    private val delete: (Int) -> Unit
) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CharacterViewHolder(layoutInflater.inflate(R.layout.character, parent, false))
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.load(characters[position], delete)
    }

    override fun getItemCount(): Int {
        return characters.size
    }


    class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = CharacterBinding.bind(view)
        fun load(character: Character, delete: (Int) -> Unit) {
            with(binding) {
                textViewNombreCharacter.text = character.name
                imageViewThumbnail.load(character.image.path + "." + character.image.extension)
                buttonDelete.setOnClickListener {
                    delete(adapterPosition)
                }
            }
        }
    }
}