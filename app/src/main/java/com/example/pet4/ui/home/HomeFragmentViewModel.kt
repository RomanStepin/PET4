package com.example.pet4.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.example.pet4.data.models.Place
import kotlinx.coroutines.flow.Flow

abstract class HomeFragmentViewModel(application: Application) : AndroidViewModel(application) {

    @ExperimentalPagingApi
    abstract fun getFlow(): Flow<PagingData<Place>>
}