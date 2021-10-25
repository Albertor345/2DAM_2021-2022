package alberto.gonzalez.crudpersonasv2.ui

import alberto.gonzalez.crudpersonasv2.R
import alberto.gonzalez.crudpersonasv2.databinding.ListActivityBinding
import alberto.gonzalez.crudpersonasv2.domain.Character
import alberto.gonzalez.crudpersonasv2.services.Services
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar


class ListActivity : AppCompatActivity() {
    private lateinit var activityBinding: ListActivityBinding
    private lateinit var services: Services

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ListActivityBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)
        services = Services()
        setListeners()
        loadList()
    }

    private fun setListeners() {
        with(activityBinding) {
            floatingActionButton.setOnClickListener {
                addItem(null)
            }
        }
    }

    fun delete(index: Int) {
        MaterialAlertDialogBuilder(this)
            .setTitle(getString(R.string.delete_alert_dialog_title))
            .setMessage(getString(R.string.delete_alert_dialog_message))
            .setNegativeButton(getString(R.string.delete_alert_dialog_negative_button_text)) { view, _ ->
                view.dismiss()
            }
            .setPositiveButton(getString(R.string.delete_alert_dialog_positive_button_text)) { view, _ ->
                var character = services.getCharacter(index)
                services.removeCharacter(character)
                view.dismiss()
                Snackbar.make(
                    this,
                    activityBinding.recycler,
                    "Se ha borrado una entrada de la lista ¿Deseas recuperarla?",
                    Snackbar.LENGTH_LONG
                ).setAction("Deshacer") {
                    addItem(character)
                }.show()
            }
            .setCancelable(false)
            .create().show()
    }

    fun details() {

    }

    fun addItem(character: Character?) {
        character?.let {
            services.addCharacter(character)
        } ?: showDialog()

    }

    fun loadList() {
        services.getListaCharacters(assets.open(getString(R.string.dataFile)))?.let {
            activityBinding.recycler.adapter = CharacterAdapter(it, ::delete)
            activityBinding.recycler.layoutManager = GridLayoutManager(this, 1)
        }
    }

    fun showDialog() {

        /*val dialog = Dialog(this, android.R.style.Theme_Material_Light_Dialog)
        dialog.setContentView(R.layout.add_fragment)
        dialog.show()*/

        val addCharacterDialogFragment = AddCharacterDialogFragment()
        addCharacterDialogFragment.show(supportFragmentManager, "addCharacterDialogFragmentDialog")

        /*val fragmentAddCharacter = AddCharacterDialogFragment().display(supportFragmentManager)

        // The device is using a large layout, so show the fragment as a dialog
        fragmentAddCharacter.show(fragmentManager, "dialog")*/

        // The device is smaller, so show the fragment fullscreen
        /*val transaction = supportFragmentManager.beginTransaction()
        // For a little polish, specify a transition animation
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        // To make it fullscreen, use the 'content' root view as the container
        // for the fragment, which is always the root view for the activity
        transaction
            .add(android.R.id.content, addCharacterDialogFragment)
            .addToBackStack(null)
            .commit()*/
    }
}



