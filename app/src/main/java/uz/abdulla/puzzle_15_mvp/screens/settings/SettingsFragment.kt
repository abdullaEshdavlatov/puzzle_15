package uz.abdulla.puzzle_15_mvp.screens.settings

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import uz.abdulla.puzzle_15_mvp.R
import uz.abdulla.puzzle_15_mvp.databinding.DeleteDialogBinding
import uz.abdulla.puzzle_15_mvp.databinding.ExitDialogBinding
import uz.abdulla.puzzle_15_mvp.databinding.FragmentSettingsBinding

class SettingsFragment: Fragment(R.layout.fragment_settings), SettingsContract.View {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private lateinit var presenter: SettingsContract.Presenter

    private lateinit var deleteDialogBinding: DeleteDialogBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSettingsBinding.bind(view)

        presenter = SettingsPresenter(this,SettingsRepository(requireContext()))

        binding.switchMusic.setOnCheckedChangeListener { buttonView, isChecked ->
            presenter.setMusicState(isChecked)
        }
        binding.switchSound.setOnCheckedChangeListener { buttonView, isChecked ->
            presenter.setSoundState(isChecked)
        }

        binding.icBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.clearAllRecords.setOnClickListener {
            showDeleteDialog()
        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun setMusicSwitchState(state: Boolean) {
        binding.switchMusic.isChecked = state
    }

    override fun setSoundSwitchState(state: Boolean) {
        binding.switchSound.isChecked = state
    }

    private fun showDeleteDialog() {
        val builder = AlertDialog.Builder(requireContext())
        deleteDialogBinding = DeleteDialogBinding.inflate(layoutInflater)
        val dialog = builder.create()
        dialog.setView(deleteDialogBinding.root)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        deleteDialogBinding.btnYes.setOnClickListener {
            presenter.clearAllRecords()
            Toast.makeText(requireContext(), "All records have been deleted", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        deleteDialogBinding.btnNo.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}