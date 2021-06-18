package com.example.pet4.di.modules

import android.content.Context
import com.example.pet4.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {
    @Provides
    @Singleton
    fun getApp(): App {
        return app
    }

    @Provides
    @Singleton
    fun getContext(): Context {
        return app.applicationContext
    }
}