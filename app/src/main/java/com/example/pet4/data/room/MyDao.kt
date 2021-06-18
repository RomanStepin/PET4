package com.example.pet4.data.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pet4.data.models.Place

@Dao
interface MyDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(users: List<Place>)

    @Query("SELECT * FROM Place")
    fun pagingSource(): PagingSource<Int, Place>

    @Query("SELECT * FROM Place WHERE primKey = :key")
    suspend fun getOne(key: Long): List<Place>

    @Query("SELECT primKey FROM Place ORDER BY primKey LIMIT 1")
    suspend fun getMinKey(): Long?

    @Query("DELETE FROM Place")
    suspend fun clearAll()

}