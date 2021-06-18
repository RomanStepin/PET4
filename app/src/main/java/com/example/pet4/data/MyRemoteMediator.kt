package com.example.pet4.di.modules

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.pet4.data.models.Place
import com.example.pet4.data.retrofit.Api
import com.example.pet4.data.room.AppDatabase
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class MyRemoteMediator(
        private val database: AppDatabase,
        private val api: Api
) : RemoteMediator<Int, Place>() {
    val userDao = database.getDaoNotifications()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Place>
    ): MediatorResult {
        return try {
            var loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = false)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        return MediatorResult.Success(
                                endOfPaginationReached = false
                        )
                    }
                    lastItem.coord.lon.toInt()
                }
            }
            if (loadKey == null)  {
                loadKey = 0
            }

            // увеличивается долгота для следующего запроса
            val response = api.getArticles(lon = loadKey + 5)
            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    userDao.clearAll()
                }
                userDao.insertAll(response.list)
            }

            // Пустой лист там никогда не придет, сигналом к завершению будет ошибка парсинга.
            MediatorResult.Success(endOfPaginationReached = response.list.isEmpty())


        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        } catch (e: NullPointerException) {
            MediatorResult.Error(e)
        }
    }
}