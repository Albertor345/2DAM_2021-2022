package com.example.examen_moviles_1eva_albertogonzalez.data.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.example.examen_moviles_1eva_albertogonzalez.R
import com.example.examen_moviles_1eva_albertogonzalez.data.ui.adapters.PuntoAdapter
import com.example.examen_moviles_1eva_albertogonzalez.data.ui.delete.DeletePuntosActivity
import com.example.examen_moviles_1eva_albertogonzalez.data.ui.domain.Mensaje
import com.example.examen_moviles_1eva_albertogonzalez.data.ui.domain.Punto
import com.example.examen_moviles_1eva_albertogonzalez.databinding.MainActivityBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    private lateinit var adapter: PuntoAdapter

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observers()
        setListeners()
        loadList()
        createInitialData()
    }

    private fun observers() {
        viewModel.puntos.observe(this, {
            adapter.submitList(it)
        })
        viewModel.error.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun setListeners() {
        binding.buttonAddMensaje.setOnClickListener {
            addMensaje()
        }
    }


    private fun addMensaje() {
        try {
            with(binding) {
                if (!textviewAutor.editText?.text.isNullOrEmpty()
                    && !textviewMensaje.editText?.text.isNullOrEmpty()
                    && !textviewIDPunto.editText?.text.isNullOrEmpty()
                ) {
                    val mensaje = Mensaje(
                        null,
                        textviewAutor.editText?.text.toString(),
                        textviewMensaje.editText?.text.toString(),
                        textviewIDPunto.editText?.text.toString().toLong()
                    )
                    viewModel.addMensaje(mensaje)

                } else {
                    Snackbar.make(
                        this@MainActivity,
                        binding.recycler,
                        getString(R.string.add_mensaje_not_data_introduced_snackbar_text),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
                clear()
            }
        } catch (ex: NumberFormatException) {
            Snackbar.make(
                this@MainActivity,
                binding.recycler,
                "Error",
                Snackbar.LENGTH_LONG
            ).show()
            Timber.log(1, ex)
        }

    }


    private fun loadId(punto: Punto) {
        with(binding) {
            textviewIDPunto.editText?.setText(punto.id.toString())
        }
    }

    private fun loadList() {
        binding.recycler.adapter = PuntoAdapter(object : PuntoAdapter.PuntoAdapterActions {
            override fun loadId(punto: Punto) {
                this@MainActivity.loadId(punto)
            }

            override fun loadMensajes(punto: Punto) {}
        })
        adapter = binding.recycler.adapter as PuntoAdapter
        viewModel.getPuntos()
    }

    private fun clear() {
        with(binding) {
            textviewAutor.editText?.text?.clear()
            textviewMensaje.editText?.text?.clear()
            textviewIDPunto.editText?.text?.clear()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_activity_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_save -> {
                loadDeleteActivity()
                true
            }
            else -> false
        }
        return super.onOptionsItemSelected(item)
    }
    private fun loadDeleteActivity() {
        val intent = Intent(this, DeletePuntosActivity::class.java)
        startActivity(intent)
    }


    private fun createInitialData() {
        val r = Random(100)
        val list = mutableListOf<Punto>()

        while (list.size < 10) {
            list.add(Punto(null, r.nextDouble(), r.nextDouble(), emptyList()))
        }
        viewModel.addPuntos(list)
    }


}







