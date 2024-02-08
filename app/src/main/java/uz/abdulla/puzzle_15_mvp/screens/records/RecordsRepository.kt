package uz.abdulla.puzzle_15_mvp.screens.records

import android.content.Context
import uz.abdulla.puzzle_15_mvp.database.HighScoreDatabase
import uz.abdulla.puzzle_15_mvp.database.HighScores
import uz.abdulla.puzzle_15_mvp.database.HighScoresDao
import uz.abdulla.puzzle_15_mvp.screens.game.GameContract

class RecordsRepository(
    private val context: Context
) : RecordsContract.Repository{
    override fun getHighScores(): List<HighScores> {
        val db = HighScoreDatabase.getDatabaseInstance(context)
        return db.highScoreDao().getHighScores()
    }

}