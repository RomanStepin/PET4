package com.example.pet4.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pet4.data.models.Place
import com.example.pet4.data.room.AppDatabase
import retrofit2.HttpException
import java.io.IOException

class MyPagingSource(val database: AppDatabase) : PagingSource<Int, Place>() {

    val dao = database.getDaoNotifications()

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Place> {
        // Из базы если читать - то по автогенерящемуся ключу
            var pageNumber = 0
            val minKey = dao.getMinKey()
            if (minKey != null)
                pageNumber = params.key ?: minKey.toInt()

    return try {
        val response = dao.getOne(pageNumber.toLong())
        val prevKey = if (pageNumber > 0) pageNumber - 1 else null

        val nextKey = if (response.isNotEmpty()) pageNumber + 1 else null
        LoadResult.Page(
            data = response,
            prevKey = prevKey,
            nextKey = nextKey
        )

    } catch (e: IOException) {
            Log.d("LOGGG", "IOException" + e.toString())
            LoadResult.Error(e)
        } catch (e: HttpException) {
            Log.d("LOGGG", "HttpException" + e.toString())
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Place>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                    ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}