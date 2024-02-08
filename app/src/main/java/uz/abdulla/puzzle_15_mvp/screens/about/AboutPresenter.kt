package uz.abdulla.puzzle_15_mvp.screens.about

class AboutPresenter(
    private val view: AboutContract.View,
    private val repository: AboutContract.Repository
): AboutContract.Presenter {
    override fun init() {
        view.setData(repository.getData())
    }
}