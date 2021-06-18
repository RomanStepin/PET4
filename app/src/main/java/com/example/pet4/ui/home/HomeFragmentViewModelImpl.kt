package com.example.pet4.ui.home

import android.app.Application
import androidx.paging.*
import com.example.pet4.data.DataRepository
import com.example.pet4.data.models.Place
import kotlinx.coroutines.flow.Flow

class HomeFragmentViewModelImpl(application: Application) : HomeFragmentViewModel(application) {
    @ExperimentalPagingApi
    var dataRepository = DataRepository.getInstance()

    @ExperimentalPagingApi
    override fun getFlow(): Flow<PagingData<Place>> {
        return dataRepository.getPagingDataFlow()
    }

}