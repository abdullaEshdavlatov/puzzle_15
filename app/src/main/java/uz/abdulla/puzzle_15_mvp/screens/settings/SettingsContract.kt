package uz.abdulla.puzzle_15_mvp.screens.settings

interface SettingsContract {
    interface Presenter{
        fun clearAllRecords()
        fun getLastMusicState()
        fun getLastSoundState()
        fun setMusicState(state: Boolean)
        fun setSoundState(state: Boolean)
    }

    interface View{
        fun setMusicSwitchState(state: Boolean)
        fun setSoundSwitchState(state: Boolean)
    }

    interface Repository{
        fun deleteAllRecordsFromDatabase()
        fun getMusicState(): Boolean?
        fun getSoundState(): Boolean?
        fun saveMusicState(state: Boolean)
        fun saveSoundState(state: Boolean)
    }
}