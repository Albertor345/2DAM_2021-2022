package com.example.crudpersonasv3.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.example.crudpersonasv3.R
import com.example.crudpersonasv3.databinding.AddFragmentBinding
import com.example.crudpersonasv3.ui.domain.CharacterUI
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class AddCharacterDialogFragment(val getCharacters: () -> Unit) : DialogFragment() {
    private lateinit var binding: AddFragmentBinding

    private val viewModel: MainViewModel by viewModels()
    private var selectedDate: String? = null


    fun display(
        fragmentManager: FragmentManager,
    ): AddCharacterDialogFragment {
        val dialog = AddCharacterDialogFragment(getCharacters)
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
        savedInstanceState: Bundle?
    ): View {
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
                dismiss()
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

        picker.show(parentFragmentManager, getString(R.string.datePicker))

    }

    private fun saveCharacter() {
        with(binding) {
            val name = characterName.editText?.text.toString()
            val description = characterDescription.editText?.text.toString()
            if (!name.isNullOrEmpty() && !description.isNullOrEmpty()) {
                viewModel.addCharacter(
                    CharacterUI(
                        null,
                        name,
                        description,
                        modified = selectedDate ?: LocalDate.now()
                            .format(DateTimeFormatter.ofPattern("dd MM yyyy")),
                        getString(R.string.image),
                        emptyList(),
                        emptyList()
                    )
                )
                dismiss()
                getCharacters()
            } else {
                characterName.startAnimation(
                    AnimationUtils.loadAnimation(
                        activity,
                        R.anim.shake_animation
                    )
                )
                characterDescription.startAnimation(
                    AnimationUtils.loadAnimation(
                        activity,
                        R.anim.shake_animation
                    )
                )
            }
        }
    }

    private fun resetFragmentContent() {
        with(binding) {
            characterName.editText?.setText("")
            characterDescription.editText?.setText("")
            selectedDate = null
        }
    }

}