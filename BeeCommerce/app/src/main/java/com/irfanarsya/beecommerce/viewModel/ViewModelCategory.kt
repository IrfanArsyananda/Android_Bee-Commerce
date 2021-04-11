package com.irfanarsya.beecommerce.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.irfanarsya.beecommerce.dataSource.category.CategoryDataFactory
import com.irfanarsya.beecommerce.dataSource.home.HomeDataFactory
import com.irfanarsya.beecommerce.model.CategoryItem
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class ViewModelCategory: ViewModel(){

    var exucutor : Executor
    var categoryData : LiveData<PagedList<CategoryItem>>

    init {

        exucutor = Executors.newFixedThreadPool(5)

        var categoryFactory = CategoryDataFactory()

        var pageListConfig = PagedList.Config.Builder()
                .setPageSize(10)
                .setInitialLoadSizeHint(20)
                .setEnablePlaceholders(false)
                .build()

        categoryData = LivePagedListBuilder(categoryFactory,pageListConfig)
                .setFetchExecutor(exucutor)
                .build()
    }

    fun getCategory (): LiveData<PagedList<CategoryItem>> {
        return categoryData
    }

}