package com.example.pet4

import android.app.Application
import com.example.pet4.di.components.DaggerRepositoryComponent
import com.example.pet4.di.components.RepositoryComponent
import com.example.pet4.di.modules.AppModule
import com.example.pet4.di.modules.NetworkModule
import com.example.pet4.di.modules.RoomModule



class App: Application() {
    companion object {
        lateinit var repositoryComponent: RepositoryComponent
    }

    init {
        repositoryComponent = DaggerRepositoryComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule())
                .roomModule(RoomModule())
                .build()
    }
}