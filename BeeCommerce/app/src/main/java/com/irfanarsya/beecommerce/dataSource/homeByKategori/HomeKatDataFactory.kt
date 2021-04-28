package com.irfanarsya.beecommerce.dataSource.homeByKategori

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.irfanarsya.beecommerce.model.DataItem

class HomeKatDataFactory(var id: Int): DataSource.Factory<Long, DataItem>() {

    var mutableLivedata : MutableLiveData<HomeKatDataSource>
    var homeKatDataSource : HomeKatDataSource

    init {
        mutableLivedata = MutableLiveData()
        homeKatDataSource = HomeKatDataSource(id)
    }

    override fun create(): DataSource<Long, DataItem> {
        mutableLivedata.postValue(homeKatDataSource)
        return homeKatDataSource

    }

    fun getMutableLiveData(): MutableLiveData<HomeKatDataSource> {
        return mutableLivedata
    }

}