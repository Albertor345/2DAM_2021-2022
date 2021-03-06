package alberto.gonzalez.pantallacrudpersonas.ui

import alberto.gonzalez.pantallacrudpersonas.domain.Persona
import alberto.gonzalez.pantallacrudpersonas.R
import alberto.gonzalez.pantallacrudpersonas.databinding.PersonaBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PersonaAdapter(private val personas: ArrayList<Persona>) :
    RecyclerView.Adapter<PersonaAdapter.PersonaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PersonaViewHolder(layoutInflater.inflate(R.layout.persona, parent, false))
    }

    override fun onBindViewHolder(holder: PersonaViewHolder, position: Int) {
        holder.load(personas[position])
    }

    override fun getItemCount(): Int {
        return personas.size
    }

    class PersonaViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val binding = PersonaBinding.bind(view)
        fun load(persona: Persona) {
            binding.textViewPersona.text = persona.toString()
        }
    }
}