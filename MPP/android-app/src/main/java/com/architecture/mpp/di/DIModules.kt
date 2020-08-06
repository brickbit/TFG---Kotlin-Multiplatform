package com.architecture.mpp.di

import android.content.Context
import data.api.ApiService
import data.local.LocalSource
import data.local.LocalSourceImp
import data.network.NetworkSource
import data.network.NetworkSourceImp
import data.preferences.PreferencesSource
import data.preferences.PreferencesSourcesImpl
import data.repository.RepositoryImp
import data.repository.Repository
import domain.error.ErrorHandler
import domain.executor.Executor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

fun appModule(context: Context) = Kodein.Module("appModule") {
    bind<Context>() with singleton { context }
    bind<Executor>() with singleton { Executor() }
    bind<ErrorHandler>() with singleton { ErrorHandler() }

}

val domainModule = Kodein.Module("domainModule") {

}

val dataModule = Kodein.Module("dataModule") {
    bind<Repository>() with singleton {
        RepositoryImp(
            network = instance(),
            local = instance(),
            preferences = instance()
        )
    }

    bind<ApiService>() with singleton {ApiService()}

    bind<NetworkSource>() with singleton { NetworkSourceImp(apiService = instance()) }
    bind<LocalSource>() with singleton { LocalSourceImp() }
    bind<PreferencesSource>() with singleton { PreferencesSourcesImpl() }
}
