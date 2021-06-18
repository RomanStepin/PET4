package com.example.pet4.data.retrofit

import com.example.pet4.data.models.NewsRequest
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {
    // Получаем список городов вокруг точки с указанными координатами. В RemoteMediator-е я прохожусь по долготе от 0 с шагом 5.
    // В результате поток данных - кучки городов вокруг этих координат.

    @GET("find?lat=50&cnt=20&appid=87c047d7fc38e4609be3cc4a388e11f6")
    suspend fun getArticles(
            @Query("lon") lon: Int
    ): NewsRequest
}