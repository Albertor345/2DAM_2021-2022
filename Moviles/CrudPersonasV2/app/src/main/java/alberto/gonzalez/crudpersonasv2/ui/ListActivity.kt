package alberto.gonzalez.crudpersonasv2.ui

import alberto.gonzalez.crudpersonasv2.R
import alberto.gonzalez.crudpersonasv2.data.Repository
import alberto.gonzalez.crudpersonasv2.databinding.ListActivityBinding
import alberto.gonzalez.crudpersonasv2.domain.Character
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import java.util.*


class ListActivity : AppCompatActivity() {
    private lateinit var binding: ListActivityBinding
    private lateinit var repository: Repository
    private lateinit var sheetBehavior: BottomSheetBehavior<View>
    private lateinit var adapter: CharacterAdapter

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
                toggleBackdrop()
            }
            toolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_reset -> {
                        clearFilterFields()
                        true
                    }
                    else -> false
                }
            }
            textviewSearch.editText?.addTextChangedListener(filterByName())

            textViewDateRange.editText?.setOnFocusChangeListener(createDatePicker())


        }
    }

    private fun addItem(character: Character?, index: Int) {
        character?.let {
            repository.addCharacter(character, index)
        } ?: showDialog()

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
                repository.removeCharacter(index)
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
            this@ListActivity,
            Pair(image, getString(R.string.pair1ActivityOptionsTag)),
            Pair(name, getString(R.string.pair2ActivityOptionsTag))
        )
        ActivityCompat.startActivity(this@ListActivity, intent, activityOptions.toBundle())
    }

    private fun loadList() {
        repository.getList().let {
            binding.recycler.adapter = CharacterAdapter(it, ::delete, ::details)
            adapter = binding.recycler.adapter as CharacterAdapter
            binding.recycler.layoutManager = GridLayoutManager(this, 1)
        }
    }

    private fun showDialog() = AddCharacterDialogFragment().display(supportFragmentManager)

    private fun toggleBackdrop() {
        if (sheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
        } else {
            sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        binding.textviewSearch.clearFocus()
    }

    private fun clearFilterFields() {
        with(binding) {
            if (textviewSearch.editText?.text!!.isNotEmpty()) {
                textviewSearch.editText?.setText("")
            }
            if (textViewDateRange.editText?.text!!.isNotEmpty()) {
                textViewDateRange.editText?.setText("")
            }
        }
        if (sheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
            sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    private fun filterByName(): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
            }

            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                adapter.filter.filter(binding.textviewSearch.editText?.text)
            }
        }
    }

    private fun filterByDate(search: String) {
        adapter.filter.filter(search)
    }

    private fun createDatePicker(): View.OnFocusChangeListener {
        return object : View.OnFocusChangeListener {
            override fun onFocusChange(v: View?, hasFocus: Boolean) {
                if (hasFocus) {
                    val picker = MaterialDatePicker.Builder.datePicker()
                        .setTitleText(getString(R.string.datePickerTitle))
                        .build()
                    picker.addOnPositiveButtonClickListener {
                        filterByDate(R.string.datePicker.toString() + picker.selection.toString())
                    }
                    picker.addOnNegativeButtonClickListener {
                        binding.textViewDateRange.clearFocus()
                        toggleBackdrop()
                    }
                    picker.addOnCancelListener {
                        binding.textViewDateRange.clearFocus()
                        toggleBackdrop()
                    }
                    picker.addOnDismissListener {
                        binding.textViewDateRange.clearFocus()
                        toggleBackdrop()
                    }
                    picker.show(supportFragmentManager, getString(R.string.datePicker))

                }
            }
        }
    }
}






