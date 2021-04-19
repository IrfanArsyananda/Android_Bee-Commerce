package com.irfanarsya.beecommerce.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.irfanarsya.beecommerce.dataSource.order.OrdersDataFactory
import com.irfanarsya.beecommerce.model.DataItem
import com.irfanarsya.beecommerce.model.DataItemGO
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class ViewModelOrders : ViewModel() {

    var exucutor: Executor = Executors.newFixedThreadPool(5)

//    var ordersFactory: OrdersDataFactory? = null
//    var userId: Int = 0

    fun setOrderId(userId: Int) {

        var ordersFactory = OrdersDataFactory(userId)

        var pageListConfig = PagedList.Config.Builder()
                .setPageSize(10)
                .setInitialLoadSizeHint(10)
                .setEnablePlaceholders(false)
                .build()

        var ordersData: LiveData<PagedList<DataItemGO>> = LivePagedListBuilder(ordersFactory, pageListConfig)
                .setFetchExecutor(exucutor)
                .build()

    }

//    fun getOrders(id: Int): LiveData<PagedList<DataItemGO>> {
//        setOrderId(id)
//        return ordersData
//    }


//    init {
//
//        exucutor = Executors.newFixedThreadPool(5)
//
//        var userId = 20
//        var ordersFactory = OrdersDataFactory(userId)
//
//        var pageListConfig = PagedList.Config.Builder()
//                .setPageSize(10)
//                .setInitialLoadSizeHint(10)
//                .setEnablePlaceholders(false)
//                .build()
//
//        ordersData = LivePagedListBuilder(ordersFactory,pageListConfig)
//                .setFetchExecutor(exucutor)
//                .build()
//    }


//    fun setID(): Int{
//        val id :Int = userId
//        return id
//    }

}