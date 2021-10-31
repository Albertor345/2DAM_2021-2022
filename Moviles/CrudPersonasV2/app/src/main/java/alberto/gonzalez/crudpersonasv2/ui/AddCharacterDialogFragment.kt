package alberto.gonzalez.crudpersonasv2.ui

import alberto.gonzalez.crudpersonasv2.R
import alberto.gonzalez.crudpersonasv2.databinding.AddFragmentBinding
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager


class AddCharacterDialogFragment : DialogFragment() {
    private lateinit var binding: AddFragmentBinding

    fun display(fragmentManager: FragmentManager): AddCharacterDialogFragment {
        val dialog = AddCharacterDialogFragment()
        dialog.show(fragmentManager, "addCharacterDialog")
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
        binding = AddFragmentBinding.inflate(layoutInflater)
        return inflater.inflate(R.layout.add_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setOnMenuItemClickListener {
            dismiss()
            true
        }
    }

    override fun setHasOptionsMenu(hasMenu: Boolean) {
        super.setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.add_dialog_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                true
            }
            R.id.action_reset -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}