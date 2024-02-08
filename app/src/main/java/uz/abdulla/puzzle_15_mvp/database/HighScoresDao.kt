package uz.abdulla.puzzle_15_mvp.database


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HighScoresDao {
    @Insert
    fun insert(highScores: HighScores)

    @Query("DELETE FROM high_scores_table")
    fun deleteAllScores()

    @Query("SELECT * FROM high_scores_table ORDER BY high_scores_table.scores ASC LIMIT 10")
    fun getHighScores(): List<HighScores>

}