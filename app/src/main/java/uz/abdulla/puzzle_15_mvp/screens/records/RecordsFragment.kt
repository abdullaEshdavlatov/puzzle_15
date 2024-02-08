package uz.abdulla.puzzle_15_mvp.screens.records

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.abdulla.puzzle_15_mvp.R
import uz.abdulla.puzzle_15_mvp.database.HighScores
import uz.abdulla.puzzle_15_mvp.databinding.FragmentRecordsBinding

class RecordsFragment : Fragment(R.layout.fragment_records), RecordsContract.View {

    private var _binding: FragmentRecordsBinding? = null
    private val binding get() = _binding!!

    private lateinit var presenter: RecordsContract.Presenter

    private lateinit var adapter: HighScoresAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRecordsBinding.bind(view)

        presenter = RecordsPresenter(this,RecordsRepository(binding.root.context))
        presenter.showHighScores()

        binding.icBack.setOnClickListener{
            findNavController().navigateUp()
        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun setHighScores(list: List<HighScores>) {
        adapter = HighScoresAdapter(list)
        binding.recyclerView.adapter = adapter
    }
}