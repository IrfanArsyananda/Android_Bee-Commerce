package com.irfanarsya.beecommerce.dataSource.order

import androidx.paging.PageKeyedDataSource
import com.irfanarsya.beecommerce.model.DataItemGO
import com.irfanarsya.beecommerce.network.ConfigNetwork
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class OrdersDataSource(var user: Int): PageKeyedDataSource<Long, DataItemGO>() {

//    private var user: Int? = null
//    fun setId(userID: Int){
//        user = userID
//    }

    override fun loadInitial(
            params: LoadInitialParams<Long>, callback: LoadInitialCallback<Long, DataItemGO>
    ) {
        ConfigNetwork.getRetrofit().getOrders(user,1, params.requestedLoadSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t ->
                    t.data?.let { callback.onResult(it, null, 2L) }
                }, {})
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, DataItemGO>) {
        ConfigNetwork.getRetrofit().getOrders(user,params.key, params.requestedLoadSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t ->
                    t.data?.let { callback.onResult(it, params.key + 1) }
                }, {})
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, DataItemGO>) {
    }

}