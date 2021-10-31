package alberto.gonzalez.crudpersonasv2.ui

import alberto.gonzalez.crudpersonasv2.R
import alberto.gonzalez.crudpersonasv2.data.Dao
import alberto.gonzalez.crudpersonasv2.databinding.ListActivityBinding
import alberto.gonzalez.crudpersonasv2.domain.Character
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar


class ListActivity : AppCompatActivity() {
    private lateinit var activityBinding: ListActivityBinding
    private lateinit var dao: Dao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ListActivityBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)
        dao = Dao(assets.open(getString(R.string.dataFile)))
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
                var character = dao.getCharacter(index)
                dao.removeCharacter(character)
                activityBinding.recycler.adapter?.notifyItemRemoved(index)
                view.dismiss()
                Snackbar.make(
                    this,
                    activityBinding.recycler,
                    getString(R.string.undo_snackbar_content_text),
                    Snackbar.LENGTH_LONG
                ).setAction(getString(R.string.undo_snackbar_action_text)) {
                    addItem(character, index)
                    activityBinding.recycler.adapter?.notifyItemInserted(index)
                }.show()
            }
            .setCancelable(false)
            .create().show()
    }

    fun details() {

    }

    private fun addItem(character: Character?, index: Int) {
        character?.let {
            dao.addCharacter(character, index)
        } ?: showDialog()

    }

    private fun loadList() {
        dao.getLista().let {
            activityBinding.recycler.adapter = CharacterAdapter(it, ::delete)
            activityBinding.recycler.layoutManager = GridLayoutManager(this, 1)
        }
    }

    private fun showDialog() {
        AddCharacterDialogFragment().display(supportFragmentManager)
    }


}



