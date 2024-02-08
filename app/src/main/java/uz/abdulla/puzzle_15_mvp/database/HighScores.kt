package uz.abdulla.puzzle_15_mvp.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "high_scores_table")
data class HighScores(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val scores: Int
)