package com.example.crudpersonasv3.ui.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.crudpersonasv3.R
import com.example.crudpersonasv3.databinding.AddFragmentBinding


class AddCharacterDialogFragment : DialogFragment() {
    private lateinit var binding: AddFragmentBinding

    fun display(fragmentManager: FragmentManager): AddCharacterDialogFragment {
        val dialog = AddCharacterDialogFragment()
        dialog.show(fragmentManager, "example_dialog")
        return dialog
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppTheme_FullScreenDialog)
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            dialog.window?.setWindowAnimations(R.style.AppTheme_Slide);
        }
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
        binding.toolbar.setNavigationOnClickListener {
            dismissFragment()
        }
        with(binding) {
            toolbar.title = getString(R.string.add_character_dialog_toolbar_title)
            toolbar.inflateMenu(R.menu.add_fragment_menu)
            toolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_save -> {
                        // Handle favorite icon press
                        true
                    }
                    R.id.action_reset -> {
                        resetFragmentContent()
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun resetFragmentContent() {
        with(binding){
            characterName.editText?.setText("")
            characterDescription.editText?.setText("")
        }
    }

    private fun dismissFragment() {
        dismiss()
    }

}