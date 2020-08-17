package domain.presenter

import BasePresenter
import View
import data.repository.RefreshStrategy
import data.repository.Repository
import domain.error.ErrorHandler
import domain.executor.Executor
import domain.model.MonumentMainItemDomain
import domain.model.MonumentMainListDomain
import kotlinx.coroutines.launch

class MonumentPresenter(
    private val repository: Repository,
    errorHandler: ErrorHandler,
    executor: Executor,
    view: MonumentView
): BasePresenter<MonumentView>(errorHandler,executor = executor, view = view){

    override fun attach() {
        getMonumentItems()
    }

    private fun getMonumentItems() {
        scope.launch {
            view.showProgress()
            execute { repository.getMonumentList(RefreshStrategy.NETWORK) }.fold(
                error = {view.showError(it.toString())},
                success = {
                    print(it)
                    showMonumentList(it)}
            )
            view.hideProgress()
        }
    }

    private fun showMonumentList(monumentMainListDomain: MonumentMainListDomain) {
        val itemList = toListItem(monumentMainListDomain)
        itemList.map {
            view.showMonumentList(it)
        }
    }

    private fun toListItem(monumentMainListDomain: MonumentMainListDomain): List<MonumentMainItemDomain> {
        val list = monumentMainListDomain.list.mapIndexed { _, monumentItemDto ->
            MonumentMainItemDomain(monumentItemDto.id,monumentItemDto.title,monumentItemDto.geocoordinates)
        }
        return list
    }

    fun onMonumentItemClicked(monumentMainItemDomain: MonumentMainItemDomain) {
        view.navigateToMonumentItem(monumentMainItemDomain.id.toString())
    }
}
interface MonumentView : View {
    fun navigateToMonumentItem(categoryId: String)
    fun showMonumentList(monumentListItemView: MonumentMainItemDomain)
}