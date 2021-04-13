package com.irfanarsya.beecommerce.dataSource.home

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.irfanarsya.beecommerce.model.DataItem

class HomeDataFactory : DataSource.Factory<Long,DataItem>() {

    var mutableLivedata : MutableLiveData<HomeDataSource>
    var homeDataSource : HomeDataSource

    init {
        mutableLivedata = MutableLiveData()
        homeDataSource = HomeDataSource()
    }

    override fun create(): DataSource<Long, DataItem> {
        mutableLivedata.postValue(homeDataSource)
        return homeDataSource

    }

    fun getMutableLiveData(): MutableLiveData<HomeDataSource> {
        return mutableLivedata
    }

}