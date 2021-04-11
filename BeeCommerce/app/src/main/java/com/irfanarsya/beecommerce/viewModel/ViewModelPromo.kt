package com.irfanarsya.beecommerce.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.irfanarsya.beecommerce.dataSource.promo.PromoDataFactory
import com.irfanarsya.beecommerce.model.DataItem
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class ViewModelPromo: ViewModel(){

    var exucutor : Executor
    var promoData : LiveData<PagedList<DataItem>>

    init {

        exucutor = Executors.newFixedThreadPool(5)

        var promoFactory = PromoDataFactory()

        var pageListConfig = PagedList.Config.Builder()
            .setPageSize(10)
            .setInitialLoadSizeHint(10)
            .setEnablePlaceholders(false)
            .build()

        promoData = LivePagedListBuilder(promoFactory,pageListConfig)
            .setFetchExecutor(exucutor)
            .build()
    }

    fun getPromo (): LiveData<PagedList<DataItem>> {
        return promoData
    }

}