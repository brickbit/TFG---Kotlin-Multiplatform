package com.architecture.mpp.view.activity

import BaseActivity
import android.view.View
import domain.model.MonumentDetailDomain
import domain.presenter.DetailView
import domain.presenter.MonumentDetailPresenter
import kotlinx.android.synthetic.main.activity_monument_detail.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class MonumentDetailActivity : BaseActivity<DetailView>(), DetailView {

    override val progress: View  by lazy { progressViewDetail }

    override val presenter by instance<MonumentDetailPresenter>()

    override val layoutResourceId: Int = com.architecture.mpp.R.layout.activity_monument_detail

    override val activityModule: Kodein.Module = Kodein.Module("MonumentDetailActivity") {
        bind<MonumentDetailPresenter>() with provider {
            MonumentDetailPresenter(
                executor = instance(),
                repository = instance(),
                view = this@MonumentDetailActivity,
                errorHandler = instance()
            )
        }
    }

    override fun initializeUI() {
        //Nothing to do here yet
    }

    override fun registerListeners() {
        //Nothing to do here yet
    }

    override fun showMonumentDetail(monumentDetailDomain: MonumentDetailDomain) {
        textViewTitle.text = monumentDetailDomain.title
        textViewDescription.text = monumentDetailDomain.description
        textViewAddress.text = monumentDetailDomain.address
        textViewCoordinates.text = monumentDetailDomain.geocoordinates
        textViewMail.text = monumentDetailDomain.email
        textViewPhone.text = monumentDetailDomain.phone
    }

    override fun getId(): String {
        return intent.getStringExtra(CATEGORY_ID_KEY) ?: "-1"
    }

    companion object {
        const val CATEGORY_ID_KEY = "CATEGORY_ID_KEY"
    }
}