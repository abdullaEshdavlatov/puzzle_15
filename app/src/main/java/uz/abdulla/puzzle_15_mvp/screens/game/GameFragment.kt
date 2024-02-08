package uz.abdulla.puzzle_15_mvp.screens.game

import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment

import android.view.View
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import uz.abdulla.puzzle_15_mvp.R
import uz.abdulla.puzzle_15_mvp.databinding.FragmentGameBinding
import uz.abdulla.puzzle_15_mvp.storage.AppSettings
import kotlin.math.abs

class GameFragment : Fragment(R.layout.fragment_game), GameContract.View {

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    private lateinit var presenter: GameContract.Presenter
    var emptyX = 0
    var emptyY = 0

    private var moves = 0
    private var isGameActive = true

    private val cell = Array(4){
        arrayOfNulls<TextView>(4)
    }
     private var pauseOffSet: Long = 0

    private var soundState = false
    private lateinit var soundPool: SoundPool
    private var clickSound = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentGameBinding.bind(view)

        soundState = AppSettings.sound?: true

        buildSoundPool()

        presenter = GamePresenter(GameRepository(), this)

        binding.time.start()

        binding.btnRestart.setOnClickListener{
            moves = 0
            binding.moves.text = "Moves\n${moves}"
            binding.time.base = SystemClock.elapsedRealtime()
            binding.time.start()
            presenter.shuffle()
        }

        binding.btnHome.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun init() {
    }

    override fun loadViewsAndHandleClicks() {
        for (i in 0 until binding.gridLayout.childCount){
            val y = i /4
            val x = i % 4
            cell[y][x] = binding.gridLayout.getChildAt(i) as TextView
            cell[y][x]?.setOnClickListener {
                if (canMove(y,x) && isGameActive){
                    swap(y,x)
                    playClickSound()
                    if (isGameOver()){
                        gameOver()
                    }
                }
            }

        }
    }

    override fun loadGame(list: List<Int>) {
        for (i in 0 until binding.gridLayout.childCount){
            if (list[i] == 16){
                cell[i/4][i%4]?.visibility = View.INVISIBLE
                emptyY = i / 4
                emptyX = i % 4
                continue
            }
            cell[i/4][i%4]?.apply {
                visibility = View.VISIBLE
                text = list[i].toString()
            }
        }
    }

    override fun isGameOver(): Boolean {
        var counter = 1
        for (i in 0 until binding.gridLayout.childCount-1) {
            val view = binding.gridLayout.getChildAt(i) as TextView
            if (view.text.isEmpty())
                break
            if (view.text.toString() == counter.toString())
                counter++
            else break
        }
        return counter == 16
    }

    private fun canMove(clickedY: Int, clickedX: Int): Boolean{
        if (abs(emptyY - clickedY) == 0 && abs(emptyX - clickedX) == 1 || abs(emptyY - clickedY) == 1 && abs(emptyX - clickedX) == 0)
            return true
        return false
    }

    private fun swap(clickedY: Int, clickedX: Int){
        binding.moves.text = "Moves\n${++moves}"

        cell[emptyY][emptyX]?.visibility = View.VISIBLE
        cell[emptyY][emptyX]?.text = cell[clickedY][clickedX]?.text

        cell[clickedY][clickedX]?.visibility = View.INVISIBLE
        cell[clickedY][clickedX]?.text = ""

        emptyY = clickedY
        emptyX = clickedX
    }

    fun gameOver(){
        isGameActive = false
        val directions =  GameFragmentDirections.actionGameFragmentToGameOverFragment(moves)
        findNavController().navigate(directions)
    }

    fun buildSoundPool(){
        val audioAttributes = AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .setUsage(AudioAttributes.USAGE_GAME)
            .build()
        soundPool = SoundPool.Builder().setMaxStreams(2).setAudioAttributes(audioAttributes).build()
        clickSound = soundPool.load(requireContext(),R.raw.click,1)
    }

    fun playClickSound(){
        if (soundState){
            soundPool.play(clickSound,1f,1f,1,0,1f)
        }
    }

    override fun onStop() {
        pauseOffSet = SystemClock.elapsedRealtime() - binding.time.base
        binding.time.stop()
        super.onStop()
    }

    override fun onStart() {
        binding.time.base =SystemClock.elapsedRealtime() - pauseOffSet
        binding.time.start()
        super.onStart()
    }
}