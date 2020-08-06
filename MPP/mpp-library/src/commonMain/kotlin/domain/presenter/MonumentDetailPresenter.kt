package domain.presenter

import BasePresenter
import data.repository.RefreshStrategy
import data.repository.Repository
import domain.error.ErrorHandler
import domain.executor.Executor
import domain.model.MonumentDetailDomain
import domain.model.MonumentMainListDomain
import kotlinx.coroutines.launch

class MonumentDetailPresenter(
    private val repository: Repository,
    errorHandler: ErrorHandler,
    executor: Executor,
    view: MonumentDetailPresenter.View
): BasePresenter<MonumentDetailPresenter.View>(errorHandler,executor = executor, view = view) {

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

    interface View : BasePresenter.View {
        fun showMonumentDetail(monumentDetailDomain: MonumentDetailDomain)
        fun getId():String
    }
}