package alberto.gonzalez.crudpersonasv2.ui

import alberto.gonzalez.crudpersonasv2.R
import alberto.gonzalez.crudpersonasv2.data.Repository
import alberto.gonzalez.crudpersonasv2.databinding.ListActivityBinding
import alberto.gonzalez.crudpersonasv2.domain.Character
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar


class ListActivity : AppCompatActivity() {
    private lateinit var binding: ListActivityBinding
    private lateinit var repository: Repository
    private lateinit var sheetBehavior: BottomSheetBehavior<View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ListActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        repository = Repository(assets.open(getString(R.string.dataFile)))
        init()
        setListeners()
        loadList()
    }

    private fun init() {
        sheetBehavior = BottomSheetBehavior.from(binding.contentLayout)
        sheetBehavior.isFitToContents = false
        sheetBehavior.isHideable = false
        sheetBehavior.state = (BottomSheetBehavior.STATE_EXPANDED)
    }

    private fun setListeners() {
        with(binding) {
            floatingActionButton.setOnClickListener {
                addItem(null, 0)
            }
            toolbar.setNavigationOnClickListener {
                toggleFilters()
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
                var character = repository.getCharacter(index)
                repository.removeCharacter(character)
                binding.recycler.adapter?.notifyItemRemoved(index)
                view.dismiss()
                Snackbar.make(
                    this,
                    binding.recycler,
                    getString(R.string.undo_snackbar_content_text),
                    Snackbar.LENGTH_LONG
                ).setAction(getString(R.string.undo_snackbar_action_text)) {
                    addItem(character, index)
                    binding.recycler.adapter?.notifyItemInserted(index)
                }.show()
            }
            .setCancelable(false)
            .create().show()
    }

    fun details(index: Int, image: View, name: View) {
        val intent = Intent(this@ListActivity, DetailsActivity::class.java)
        intent.putExtra(getString(R.string.characterIndex), index)

        val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this@ListActivity,  // Now we provide a list of Pair items which contain the view we can transitioning
            // from, and the name of the view it is transitioning to, in the launched activity
            Pair(image, "imageViewThumbnail"), Pair(name, "textViewNombreCharacter")
        )
        ActivityCompat.startActivity(this@ListActivity, intent, activityOptions.toBundle())
    }

    private fun addItem(character: Character?, index: Int) {
        character?.let {
            repository.addCharacter(character, index)
        } ?: showDialog()

    }

    private fun loadList() {
        repository.getLista().let {
            binding.recycler.adapter = CharacterAdapter(it, ::delete, ::details)
            binding.recycler.layoutManager = GridLayoutManager(this, 1)
        }


    }

    private fun showDialog() {
        AddCharacterDialogFragment().display(supportFragmentManager)
    }

    private fun toggleFilters() {
        if (sheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
        } else {
            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
        }
    }


}



