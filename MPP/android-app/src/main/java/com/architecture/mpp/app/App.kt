package com.architecture.mpp.app

import android.app.Application
import com.architecture.mpp.di.appModule
import com.architecture.mpp.di.dataModule
import com.architecture.mpp.di.domainModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

class App : Application(), KodeinAware {
    override val kodein by Kodein.lazy {
        import(appModule(this@App))
        import(domainModule)
        import(dataModule)
    }
}