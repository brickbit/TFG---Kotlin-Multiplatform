package domain.presenter

import BasePresenter
import View
import data.repository.RefreshStrategy
import data.repository.Repository
import domain.error.ErrorHandler
import domain.executor.Executor
import domain.model.MonumentDetailDomain
import kotlinx.coroutines.launch

class MonumentDetailPresenter(
    private val repository: Repository,
    errorHandler: ErrorHandler,
    executor: Executor,
    view: DetailView
): BasePresenter<DetailView>(errorHandler,executor = executor, view = view) {

    override fun attach() {
        view.getId()
        getMonumentDetail()
    }

    private fun getMonumentDetail() {
        scope.launch {
            view.showProgress()
            execute { repository.getMonumentItem(RefreshStrategy.NETWORK, view.getId()) }.fold(
                error = {view.showError(it.toString())},
                success = {
                    print(it)
                    showMonumentList(it)}
            )
            view.hideProgress()
        }
    }

    private fun showMonumentList(monumentDetailDomain: MonumentDetailDomain) {
        view.showMonumentDetail(monumentDetailDomain)
    }

}

interface DetailView : View {
    fun showMonumentDetail(monumentDetailDomain: MonumentDetailDomain)
    fun getId():String
}