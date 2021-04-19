package com.irfanarsya.beecommerce.dataSource.order

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.irfanarsya.beecommerce.model.DataItemGO

class OrdersDataFactory(var uId: Int): DataSource.Factory<Long, DataItemGO>() {

    var mutableLivedata : MutableLiveData<OrdersDataSource>
    var ordersDataSource : OrdersDataSource
//    var uId: Int = 0

    init {
        mutableLivedata = MutableLiveData()
        ordersDataSource = OrdersDataSource(uId)
    }

    override fun create(): DataSource<Long, DataItemGO> {
        mutableLivedata.postValue(ordersDataSource)
        return ordersDataSource

    }

    fun getMutableLiveData(): MutableLiveData<OrdersDataSource> {
        return mutableLivedata
    }

//    fun setId(id: Int){
//        uId = id
//    }

}