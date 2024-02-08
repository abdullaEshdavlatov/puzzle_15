package uz.abdulla.puzzle_15_mvp.screens.game

interface GameContract {
    interface Presenter{
        fun shuffle()
    }
    interface View{
        fun init()
        fun loadViewsAndHandleClicks()
        fun loadGame(list: List<Int>)
        fun isGameOver(): Boolean
    }

    interface Repository{
        fun loadNumbers(): List<Int>
        fun getShuffledNumbers(): List<Int>
    }
}