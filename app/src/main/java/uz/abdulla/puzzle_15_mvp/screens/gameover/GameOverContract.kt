package uz.abdulla.puzzle_15_mvp.screens.gameover

import android.icu.text.MessagePattern.Part
import nl.dionsegijn.konfetti.core.Party
import uz.abdulla.puzzle_15_mvp.database.HighScoreDatabase

interface GameOverContract {
    interface Presenter{
        fun saveToDatabase()
        fun startAnimation()
    }

    interface View{
        fun setHighScore(db: HighScoreDatabase)
        fun setAnimation(party: Party)
    }

    interface Repository{
        fun getDatabaseInstance(): HighScoreDatabase
        fun getAnimation(): Party
    }
}