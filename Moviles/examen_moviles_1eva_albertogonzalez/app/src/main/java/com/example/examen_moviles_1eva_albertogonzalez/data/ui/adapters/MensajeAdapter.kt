package com.example.examen_moviles_1eva_albertogonzalez.data.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.examen_moviles_1eva_albertogonzalez.R
import com.example.examen_moviles_1eva_albertogonzalez.data.ui.domain.Mensaje
import com.example.examen_moviles_1eva_albertogonzalez.databinding.MensajeBinding

class MensajeAdapter(private val mensajeAdapterActions: MensajeAdapterActions) :
    ListAdapter<Mensaje, MensajeAdapter.MensajeViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MensajeViewHolder {
        return MensajeViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.mensaje, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MensajeViewHolder, position: Int) = with(holder) {
        val mensaje = getItem(position)
        bind(mensaje)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    inner class MensajeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = MensajeBinding.bind(view)

        fun bind(mensaje: Mensaje) {
            with(binding) {
                autor.text = mensaje.autor
                binding.mensaje.text = mensaje.mensaje
                borrar.setOnClickListener() {
                    mensajeAdapterActions.deleteMensaje(mensaje)
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Mensaje>() {
        override fun areItemsTheSame(oldItem: Mensaje, newItem: Mensaje): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Mensaje, newItem: Mensaje): Boolean {
            return oldItem == newItem
        }
    }

    interface MensajeAdapterActions {
        fun deleteMensaje(mensaje: Mensaje)
    }
}