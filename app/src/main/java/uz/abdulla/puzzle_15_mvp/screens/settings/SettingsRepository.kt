package uz.abdulla.puzzle_15_mvp.screens.settings

import android.content.Context
import uz.abdulla.puzzle_15_mvp.database.HighScoreDatabase
import uz.abdulla.puzzle_15_mvp.storage.AppSettings

class SettingsRepository(private val context: Context): SettingsContract.Repository {
    override fun deleteAllRecordsFromDatabase() {
        val highScoreDatabase = HighScoreDatabase.getDatabaseInstance(context)
        highScoreDatabase.highScoreDao().deleteAllScores()
    }

    override fun getMusicState(): Boolean? {
        return AppSettings.music
    }

    override fun getSoundState(): Boolean? {
        return AppSettings.sound
    }

    override fun saveMusicState(state: Boolean) {
        AppSettings.music = state
    }

    override fun saveSoundState(state: Boolean) {
        AppSettings.sound = state
    }
}