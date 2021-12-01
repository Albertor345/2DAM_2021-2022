package com.example.examen_moviles_1eva_albertogonzalez.data.ui.delete

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.examen_moviles_1eva_albertogonzalez.data.ui.adapters.MensajeAdapter
import com.example.examen_moviles_1eva_albertogonzalez.data.ui.adapters.PuntoAdapter
import com.example.examen_moviles_1eva_albertogonzalez.data.ui.domain.Mensaje
import com.example.examen_moviles_1eva_albertogonzalez.data.ui.domain.Punto
import com.example.examen_moviles_1eva_albertogonzalez.databinding.DeletePuntosBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeletePuntosActivity : AppCompatActivity() {
    private lateinit var binding: DeletePuntosBinding
    private lateinit var adapterPuntos: PuntoAdapter
    private lateinit var adapterMensajes: MensajeAdapter

    private val viewModel: DeleteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DeletePuntosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observers()
        loadList()
    }

    private fun observers() {
        viewModel.puntos.observe(this, {
            adapterPuntos.submitList(it)
        })
        viewModel.mensajes.observe(this, {
            adapterMensajes.submitList(it)
        })
        viewModel.error.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }


    private fun loadList() {
        binding.recyclerPuntos.adapter = PuntoAdapter(object : PuntoAdapter.PuntoAdapterActions {
            override fun loadId(punto: Punto) {

            }

            override fun loadMensajes(punto: Punto) {
                this@DeletePuntosActivity.loadMensajes(punto)
            }
        })
        adapterPuntos = binding.recyclerPuntos.adapter as PuntoAdapter
        viewModel.getPuntos()
        binding.recyclerMensajes.adapter =
            MensajeAdapter(object : MensajeAdapter.MensajeAdapterActions {

                override fun deleteMensaje(mensaje: Mensaje) {
                    this@DeletePuntosActivity.deleteMensaje(mensaje)
                }
            })
        adapterMensajes = binding.recyclerMensajes.adapter as MensajeAdapter
    }

    private fun loadMensajes(punto: Punto) {
        viewModel.getMensajesPunto(punto)
    }


    fun deleteMensaje(mensaje: Mensaje) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Borrar Mensaje")
            .setMessage("¿Estas seguro que deseas borrar este mensaje?")
            .setNegativeButton("Cancelar") { view, _ ->
                view.dismiss()
            }
            .setPositiveButton("Borrar") { view, _ ->
                viewModel.delMensaje(mensaje)
                view.dismiss()
            }
            .setCancelable(false)
            .create().show()
    }
}