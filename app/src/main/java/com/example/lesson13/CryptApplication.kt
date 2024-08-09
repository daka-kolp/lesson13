package com.example.lesson13

import android.app.Application
import com.example.lesson13.di.cryptModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CryptApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CryptApplication)
            modules(cryptModule)
        }
    }
}