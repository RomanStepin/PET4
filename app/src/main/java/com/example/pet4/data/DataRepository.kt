package com.example.pet4.data

import androidx.paging.*
import com.example.pet4.App
import com.example.pet4.data.models.Place
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.example.pet4.data.retrofit.Api
import com.example.pet4.data.room.AppDatabase
import com.example.pet4.di.modules.MyRemoteMediator

/**
 * класс, обеспечивающий доступ к локальным данным и к удаленным
 */
@ExperimentalPagingApi
class DataRepository {

    @Inject
    lateinit var database: AppDatabase

    @Inject
    lateinit var api: Api

    init {
        App.repositoryComponent.inject(this)
    }

    companion object {
        const val DEFAULT_PAGE_SIZE = 20
        fun getInstance() = DataRepository()
    }
    /**
     * Координаты города
     * Я использую PagingSource из room, а своя реализация закоменчена. Потому что я ключи для скачки из интернета - примерные координаты,
     * а в базе лежат реальные координаты. И получается, что у них нет общего ключа ((
     */
    fun getPagingDataFlow(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<Place>> {
        return Pager(
                config = pagingConfig,
                remoteMediator = MyRemoteMediator(database, api),
                pagingSourceFactory = { database.getDaoNotifications().pagingSource()}
                //pagingSourceFactory = { MyPagingSource(database) }
        ).flow
    }

    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = true)
    }
}