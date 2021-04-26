package com.irfanarsya.beecommerce.dataSource.search

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.irfanarsya.beecommerce.dataSource.home.HomeDataSource
import com.irfanarsya.beecommerce.model.DataItem

class SearchDataFactory(var q: String): DataSource.Factory<Long, DataItem>() {

    var mutableLivedata : MutableLiveData<SearchDataSource>
    var searchDataSource : SearchDataSource

    init {
        mutableLivedata = MutableLiveData()
        searchDataSource = SearchDataSource(q)
    }

    override fun create(): DataSource<Long, DataItem> {
        mutableLivedata.postValue(searchDataSource)
        return searchDataSource

    }

    fun getMutableLiveData(): MutableLiveData<SearchDataSource> {
        return mutableLivedata
    }

}