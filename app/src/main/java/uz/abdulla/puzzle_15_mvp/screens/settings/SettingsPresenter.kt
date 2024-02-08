package uz.abdulla.puzzle_15_mvp.screens.settings

class SettingsPresenter(
    private val view: SettingsContract.View,
    private val repository: SettingsContract.Repository
): SettingsContract.Presenter {

    init {
        getLastMusicState()
        getLastSoundState()
    }
    override fun clearAllRecords() {
        repository.deleteAllRecordsFromDatabase()
    }

    override fun getLastMusicState() {
        view.setMusicSwitchState(repository.getMusicState()?:true)
    }

    override fun getLastSoundState() {
        view.setSoundSwitchState(repository.getSoundState()?:true)
    }

    override fun setMusicState(state: Boolean) {
        repository.saveMusicState(state)
    }

    override fun setSoundState(state: Boolean) {
        repository.saveSoundState(state)
    }
}