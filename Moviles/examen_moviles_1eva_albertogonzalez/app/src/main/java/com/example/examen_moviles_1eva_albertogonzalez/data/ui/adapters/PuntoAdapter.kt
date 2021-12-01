package com.example.examen_moviles_1eva_albertogonzalez.data.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.examen_moviles_1eva_albertogonzalez.R
import com.example.examen_moviles_1eva_albertogonzalez.data.ui.domain.Punto
import com.example.examen_moviles_1eva_albertogonzalez.databinding.PuntoBinding

class PuntoAdapter(private val puntoAdapterActions: PuntoAdapterActions) :
    ListAdapter<Punto, PuntoAdapter.PuntoViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PuntoViewHolder {
        return PuntoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.punto, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PuntoViewHolder, position: Int) = with(holder) {
        val punto = getItem(position)
        bind(punto)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    inner class PuntoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = PuntoBinding.bind(view)

        fun bind(punto: Punto) {
            with(binding) {
                nombrePunto.text = "Latitud: {${punto.latitud}} Longitud: {${punto.longitud}}"
                buttonDetails.setOnClickListener{
                    puntoAdapterActions.loadId(punto)
                    puntoAdapterActions.loadMensajes(punto)
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Punto>() {
        override fun areItemsTheSame(oldItem: Punto, newItem: Punto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Punto, newItem: Punto): Boolean {
            return oldItem == newItem
        }
    }

    interface PuntoAdapterActions {
        fun loadId(punto: Punto)
        fun loadMensajes(punto: Punto)
    }
}