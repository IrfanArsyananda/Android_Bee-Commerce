package com.irfanarsya.beecommerce.dataSource.category

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.irfanarsya.beecommerce.dataSource.home.HomeDataSource
import com.irfanarsya.beecommerce.model.CategoryItem

class CategoryDataFactory: DataSource.Factory<Long, CategoryItem>() {

    var mutableLivedata : MutableLiveData<CategoryDataSource>
    var categoryDataSource : CategoryDataSource

    init {
        mutableLivedata = MutableLiveData()
        categoryDataSource = CategoryDataSource()
    }

    override fun create(): DataSource<Long, CategoryItem> {
        mutableLivedata.postValue(categoryDataSource)
        return categoryDataSource

    }

    fun getMutableLiveData(): MutableLiveData<CategoryDataSource> {
        return mutableLivedata
    }
}