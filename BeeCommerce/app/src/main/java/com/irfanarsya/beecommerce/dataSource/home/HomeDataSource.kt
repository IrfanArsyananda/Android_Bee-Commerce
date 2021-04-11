package com.irfanarsya.beecommerce.dataSource.home

import androidx.paging.PageKeyedDataSource
import com.irfanarsya.beecommerce.model.DataItem
import com.irfanarsya.beecommerce.network.ConfigNetwork
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeDataSource : PageKeyedDataSource<Long, DataItem>() {

    override fun loadInitial(
        params: LoadInitialParams<Long>, callback: LoadInitialCallback<Long, DataItem>
    ) {
        ConfigNetwork.getRetrofit().getHomeProducts(1, params.requestedLoadSize)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ t ->
                t.data?.let { callback.onResult(it, null, 2L) }
            }, {})
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, DataItem>) {
        ConfigNetwork.getRetrofit().getHomeProducts(params.key, params.requestedLoadSize)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ t ->
                t.data?.let { callback.onResult(it, params.key + 1) }
            }, {})
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, DataItem>) {
    }

}