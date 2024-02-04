package com.gana.ebenezer.caleb

import android.app.Application
import com.gana.ebenezer.caleb.di.appModule
import com.gana.ebenezer.caleb.di.repositoryModule
import com.gana.ebenezer.caleb.di.remote
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                appModule, remote, repositoryModule
            )
        }
    }
}