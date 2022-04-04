package com.example.testcontactsjni.app

import android.app.Application
import com.example.data.di.DataModule
import com.example.domain.di.DomainModule
import com.example.testcontactsjni.app.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(listOf(AppModule, DataModule, DomainModule))
        }
    }
}