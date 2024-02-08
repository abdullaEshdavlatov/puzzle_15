package uz.abdulla.puzzle_15_mvp.screens.main

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.abdulla.puzzle_15_mvp.R
import uz.abdulla.puzzle_15_mvp.databinding.ExitDialogBinding
import uz.abdulla.puzzle_15_mvp.databinding.FragmentMainBinding
import uz.abdulla.puzzle_15_mvp.storage.AppSettings
import java.lang.IllegalStateException

class MainFragment : Fragment(R.layout.fragment_main) {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var exitDialogBinding: ExitDialogBinding

    private var bgMusic:MediaPlayer? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)

        if (bgMusic == null){
            bgMusic = MediaPlayer.create(requireContext(),R.raw.music)
            bgMusic?.isLooping = true
        }



        binding.btnPlay.setOnClickListener{
            stopMusic()
            findNavController().navigate(R.id.action_mainFragment_to_gameFragment)
        }

        binding.btnRecords.setOnClickListener{
            findNavController().navigate(R.id.action_mainFragment_to_recordsFragment)
        }

        binding.btnSetting.setOnClickListener{
            findNavController().navigate(R.id.action_mainFragment_to_settingsFragment)
        }

        binding.btnAbout.setOnClickListener{
            findNavController().navigate(R.id.action_mainFragment_to_aboutFragment)
        }

        binding.btnExit.setOnClickListener {
            showExitDialog()
        }


    }

    private fun showExitDialog() {
        val builder = AlertDialog.Builder(requireContext())
        exitDialogBinding = ExitDialogBinding.inflate(layoutInflater)
        val dialog = builder.create()
        dialog.setView(exitDialogBinding.root)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        exitDialogBinding.btnYes.setOnClickListener {
            dialog.dismiss()
            requireActivity().finish()
        }
        exitDialogBinding.btnNo.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun startMusic(){
        if (AppSettings.music == true){
            bgMusic?.start()
        }
        else{
            stopMusic()
        }

    }

    private fun stopMusic(){
        try {
            if (bgMusic != null && bgMusic!!.isPlaying){
                bgMusic?.pause()
                bgMusic?.stop()
                bgMusic?.release()
                bgMusic = null
            }
        } catch (e: IllegalStateException){
            e.printStackTrace()
        }
    }

    override fun onResume() {
        startMusic()
        super.onResume()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        stopMusic()
        super.onDestroy()
    }

}