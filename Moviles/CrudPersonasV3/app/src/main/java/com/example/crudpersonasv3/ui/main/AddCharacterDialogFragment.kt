package com.example.crudpersonasv3.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.example.crudpersonasv3.R
import com.example.crudpersonasv3.databinding.AddFragmentBinding
import com.example.crudpersonasv3.ui.domain.CharacterUI
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCharacterDialogFragment : DialogFragment() {
    private lateinit var binding: AddFragmentBinding

    private val viewModel: MainViewModel by viewModels()
    private lateinit var selectedDate: String

    fun display(fragmentManager: FragmentManager): AddCharacterDialogFragment {
        val dialog = AddCharacterDialogFragment()
        dialog.show(fragmentManager, "example_dialog")
        return dialog
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddFragmentBinding.inflate(layoutInflater)
        setStyle(STYLE_NORMAL, R.style.AppTheme_FullScreenDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = AddFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            toolbar.title = getString(R.string.add_character_dialog_toolbar_title)
            toolbar.inflateMenu(R.menu.add_fragment_menu)
        }
        setListeners()
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            dialog.window?.setWindowAnimations(R.style.AppTheme_Slide)
        }
    }




    private fun setListeners() {
        with(binding) {
            binding.toolbar.setNavigationOnClickListener {
                dismissFragment()
            }
            toolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_save -> {
                        saveCharacter()
                        true
                    }
                    R.id.action_reset -> {
                        resetFragmentContent()
                        true
                    }
                    else -> false
                }
            }
            buttonDate.setOnClickListener {
                selectDate()
            }
        }
    }

    private fun selectDate() {
        val picker = MaterialDatePicker.Builder.datePicker()
            .setTitleText(getString(R.string.datePickerTitle))
            .build()
        picker.addOnPositiveButtonClickListener {
            selectedDate = picker.selection.toString()
        }

        picker.showNow(parentFragmentManager, getString(R.string.datePicker))

    }

    private fun saveCharacter() {
        with(binding) {
            viewModel.addCharacter(
                CharacterUI(
                    id = -1,
                    characterName.editText?.text.toString(),
                    characterDescription.editText?.text.toString(),
                    selectedDate,
                    getString(R.string.image_not_found_location),
                    emptyList(),
                    emptyList()
                )
            )
        }
        dismissFragment()
    }

    private fun resetFragmentContent() {
        with(binding) {
            characterName.editText?.setText("")
            characterDescription.editText?.setText("")
        }
    }

    private fun dismissFragment() {
        dismiss()
    }

}