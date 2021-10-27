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
                addItem(null, 0)
            }
        }
    }

    private fun delete(index: Int) {
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
                    getString(R.string.undo_snackbar_content_text),
                    Snackbar.LENGTH_LONG
                ).setAction(getString(R.string.undo_snackbar_action_text)) {
                    addItem(character, index)
                }.show()
            }
            .setCancelable(false)
            .create().show()
    }

    fun details() {

    }

    private fun addItem(character: Character?, index: Int) {
        character?.let {
            services.addCharacter(character, index)
        } ?: showDialog()

    }

    private fun loadList() {
        services.getListaCharacters(assets.open(getString(R.string.dataFile)))?.let {
            activityBinding.recycler.adapter = CharacterAdapter(it, ::delete)
            activityBinding.recycler.layoutManager = GridLayoutManager(this, 1)
        }
    }

    private fun showDialog() {
        val addCharacterDialogFragment = AddCharacterDialogFragment()
        addCharacterDialogFragment.show(supportFragmentManager, "addCharacterDialogFragment")
    }
}



