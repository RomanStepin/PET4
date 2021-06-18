package com.example.pet4.di.components

import androidx.paging.ExperimentalPagingApi
import com.example.pet4.data.DataRepository
import com.example.pet4.di.modules.AppModule
import com.example.pet4.di.modules.NetworkModule
import com.example.pet4.di.modules.RoomModule
import dagger.Component
import javax.inject.Singleton


/**
 * Имплементирую базу и retrofit API
 */
@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, RoomModule::class])
interface RepositoryComponent {
    @ExperimentalPagingApi
    public fun inject(dataRepository: DataRepository)
}