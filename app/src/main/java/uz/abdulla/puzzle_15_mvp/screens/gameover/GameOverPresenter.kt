package uz.abdulla.puzzle_15_mvp.screens.gameover

class GameOverPresenter(
    private val view: GameOverContract.View,
    private val repository: GameOverContract.Repository
): GameOverContract.Presenter {
    override fun saveToDatabase() {
        view.setHighScore(repository.getDatabaseInstance())
    }

    override fun startAnimation() {
        view.setAnimation(repository.getAnimation())
    }
}