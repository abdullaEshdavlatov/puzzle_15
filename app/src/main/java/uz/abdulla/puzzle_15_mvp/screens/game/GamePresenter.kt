package uz.abdulla.puzzle_15_mvp.screens.game

class GamePresenter(
    private val repository: GameContract.Repository,
    private val view: GameContract.View
): GameContract.Presenter {

    init {
        repository.loadNumbers()
        view.init()
        view.loadViewsAndHandleClicks()
        view.loadGame(repository.getShuffledNumbers())
    }
    override fun shuffle() {
        view.loadGame(repository.getShuffledNumbers())
    }
}