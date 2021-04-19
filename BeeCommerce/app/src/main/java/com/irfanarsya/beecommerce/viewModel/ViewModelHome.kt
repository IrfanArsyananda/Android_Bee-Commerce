package com.irfanarsya.beecommerce.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.irfanarsya.beecommerce.dataSource.home.HomeDataFactory
import com.irfanarsya.beecommerce.local.History
import com.irfanarsya.beecommerce.model.DataItem
import com.irfanarsya.beecommerce.repository.RepositoryLocal
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class ViewModelHome: ViewModel(){

    var exucutor : Executor
    var homeData : LiveData<PagedList<DataItem>>
//    var searchData : LiveData<PagedList<DataItem>>


    init {

        exucutor = Executors.newFixedThreadPool(5)

        var homeFactory = HomeDataFactory()
//        var searchFactory = SearchDataFactory()

        var pageListConfig = PagedList.Config.Builder()
            .setPageSize(10)
            .setInitialLoadSizeHint(10)
            .setEnablePlaceholders(false)
            .build()

        homeData = LivePagedListBuilder(homeFactory,pageListConfig)
            .setFetchExecutor(exucutor)
            .build()

//        searchData = LivePagedListBuilder(searchFactory,pageListConfig)
//                .setFetchExecutor(exucutor)
//                .build()
    }

    fun getHomeProduts (): LiveData<PagedList<DataItem>> {
        return homeData
    }

//    fun getSearchProduts (): LiveData<PagedList<DataItem>> {
//        val sd = SearchDataSource()
//        sd.search("kipas")
//        return searchData
//    }

    val repoLocal = RepositoryLocal()

    var onSuccessInsert = MutableLiveData<History>()
    var onErrorInsert = MutableLiveData<Throwable>()
    var isLoading = MutableLiveData<Boolean>()

    fun insertHistory(item: History) {
        isLoading.value = true

        repoLocal.insertHistory(item
//                , {
//            onSuccessInsert.value = it
//            isLoading.value = false
//        }, {
//            onErrorInsert.value = it
//            isLoading.value = false
//        }
        )

    }

}