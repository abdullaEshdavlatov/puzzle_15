package uz.abdulla.puzzle_15_mvp.screens.records

class RecordsPresenter(
    private val view: RecordsContract.View,
    private val repository: RecordsContract.Repository
): RecordsContract.Presenter {
    override fun showHighScores() {
        view.setHighScores(repository.getHighScores())
    }
}