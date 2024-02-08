package uz.abdulla.puzzle_15_mvp.screens.records

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.abdulla.puzzle_15_mvp.database.HighScores
import uz.abdulla.puzzle_15_mvp.databinding.RvItemBinding

class HighScoresAdapter(private val list: List<HighScores>): RecyclerView.Adapter<HighScoresAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: RvItemBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(highScores: HighScores){
            binding.sequenceNumber.text = (adapterPosition + 1).toString()
            binding.moves.text = highScores.scores.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RvItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }
}