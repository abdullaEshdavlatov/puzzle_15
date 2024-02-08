package uz.abdulla.puzzle_15_mvp.screens.gameover

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import nl.dionsegijn.konfetti.core.Party
import uz.abdulla.puzzle_15_mvp.R
import uz.abdulla.puzzle_15_mvp.database.HighScoreDatabase
import uz.abdulla.puzzle_15_mvp.database.HighScores
import uz.abdulla.puzzle_15_mvp.databinding.ExitDialogBinding
import uz.abdulla.puzzle_15_mvp.databinding.FragmentGameOverBinding

class GameOverFragment : Fragment(R.layout.fragment_game_over), GameOverContract.View {

    private var _binding: FragmentGameOverBinding? = null
    private val binding get() = _binding!!

    private lateinit var exitDialogBinding: ExitDialogBinding

    private val args:GameOverFragmentArgs by navArgs()

    private lateinit var presenter: GameOverContract.Presenter



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentGameOverBinding.bind(view)

        presenter = GameOverPresenter(this,GameOverRepository(binding.root.context))
        presenter.saveToDatabase()
        presenter.startAnimation()

        binding.gameOverInfo.text = "You won the game in ${args.moves} moves"

        binding.btnRestart.setOnClickListener {
            findNavController().navigate(R.id.action_gameOverFragment_to_gameFragment)
        }

        binding.btnMainMenu.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnExit.setOnClickListener {
            showExitDialog()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun setHighScore(db: HighScoreDatabase) {
        val highScores = HighScores(0,args.moves)
        db.highScoreDao().insert(highScores)
    }

    override fun setAnimation(party: Party) {
        binding.konfettiView.start(party)
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

}