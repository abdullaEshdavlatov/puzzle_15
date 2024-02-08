package uz.abdulla.puzzle_15_mvp.screens.records

import uz.abdulla.puzzle_15_mvp.database.HighScores

interface RecordsContract {
    interface Presenter{
        fun showHighScores()
    }

    interface View{
        fun setHighScores(list: List<HighScores>)
    }

    interface Repository{
        fun getHighScores(): List<HighScores>
    }
}