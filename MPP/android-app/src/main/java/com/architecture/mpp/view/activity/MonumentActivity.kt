package com.architecture.mpp.view.activity

import BaseActivity
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.architecture.mpp.R
import com.architecture.mpp.navigator.navigateToDetailActivity
import com.architecture.mpp.view.adapter.MonumentAdapter
import domain.model.MonumentMainItemDomain
import domain.presenter.MonumentPresenter
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class MonumentActivity : BaseActivity<MonumentPresenter.View>(), MonumentPresenter.View {

    override val progress: View by lazy { progressViewMonument }

    override val presenter: MonumentPresenter by instance<MonumentPresenter>()

    override val layoutResourceId: Int = R.layout.activity_main

    override val activityModule: Kodein.Module = Kodein.Module("MonumentListActivity") {
        bind<MonumentPresenter>() with provider {
            MonumentPresenter(
                executor = instance(),
                repository = instance(),
                view = this@MonumentActivity,
                errorHandler = instance()
            )
        }
    }

    override fun initializeUI() {
        recyclerMonument.adapter = monumentAdapter
        recyclerMonument.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    override fun registerListeners() {
        //Nothing to do here yet
    }

    override fun navigateToMonumentItem(categoryId: String) {
        navigateToDetailActivity(this, categoryId)
    }

    override fun showMonumentList(monumentListItemView: MonumentMainItemDomain) {
        monumentAdapter.add(monumentListItemView)
    }

    private val monumentAdapter = MonumentAdapter {
        presenter.onMonumentItemClicked(it)
    }
}