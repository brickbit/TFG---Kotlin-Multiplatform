package com.architecture.mpp.di

import android.content.Context
import domain.error.ErrorHandler
import domain.executor.Executor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

fun appModule(context: Context) = Kodein.Module("appModule") {
    bind<Context>() with singleton { context }
    bind<Executor>() with singleton { Executor() }
    bind<ErrorHandler>() with singleton { ErrorHandler() }

}

val domainModule = Kodein.Module("domainModule") {

}

val dataModule = Kodein.Module("dataModule") {

}
