package com.irfanarsya.beecommerce.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.irfanarsya.beecommerce.dataSource.home.HomeDataFactory
import com.irfanarsya.beecommerce.model.DataItem
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class ViewModelHome: ViewModel(){

    var exucutor : Executor
    var homeData : LiveData<PagedList<DataItem>>

    init {

        exucutor = Executors.newFixedThreadPool(5)

        var homeFactory = HomeDataFactory()

        var pageListConfig = PagedList.Config.Builder()
            .setPageSize(10)
            .setInitialLoadSizeHint(10)
            .setEnablePlaceholders(false)
            .build()

        homeData = LivePagedListBuilder(homeFactory,pageListConfig)
            .setFetchExecutor(exucutor)
            .build()
    }

    fun getHomeProduts (): LiveData<PagedList<DataItem>> {
        return homeData
    }

}