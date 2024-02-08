package uz.abdulla.puzzle_15_mvp.screens.about

import uz.abdulla.puzzle_15_mvp.model.About

interface AboutContract {
    interface Presenter{
        fun init()
    }

    interface View{
        fun setData(about: About)
    }

    interface Repository{
        fun getData(): About
    }
}