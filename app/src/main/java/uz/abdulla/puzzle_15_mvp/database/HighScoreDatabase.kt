package uz.abdulla.puzzle_15_mvp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HighScores::class], version = 1, exportSchema = false)
abstract class HighScoreDatabase: RoomDatabase() {
    abstract fun highScoreDao(): HighScoresDao
    companion object{
        private var instance: HighScoreDatabase? = null

        fun getDatabaseInstance(context: Context): HighScoreDatabase{
            return instance?: Room.databaseBuilder(context,HighScoreDatabase::class.java,"high_score_database").allowMainThreadQueries().build()
        }


    }
}