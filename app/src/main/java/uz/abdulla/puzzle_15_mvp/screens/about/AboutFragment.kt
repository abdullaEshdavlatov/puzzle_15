package uz.abdulla.puzzle_15_mvp.screens.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import uz.abdulla.puzzle_15_mvp.R
import uz.abdulla.puzzle_15_mvp.databinding.FragmentAboutBinding
import uz.abdulla.puzzle_15_mvp.model.About

class AboutFragment : Fragment(R.layout.fragment_about), AboutContract.View {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    private lateinit var presenter: AboutContract.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAboutBinding.bind(view)

        binding.icBack.setOnClickListener {
            findNavController().navigateUp()
        }
        presenter = AboutPresenter(this,AboutRepository(requireContext()))
        presenter.init()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun setData(about: About) {
        binding.appVersion.text = "Version ${about.appVersion}"
        binding.appInfo.text = about.appInfo
    }
}