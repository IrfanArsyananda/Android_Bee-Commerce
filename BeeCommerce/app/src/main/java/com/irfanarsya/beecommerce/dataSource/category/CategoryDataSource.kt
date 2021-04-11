package com.irfanarsya.beecommerce.dataSource.category

import androidx.paging.PageKeyedDataSource
import com.irfanarsya.beecommerce.model.CategoryItem
import com.irfanarsya.beecommerce.network.ConfigNetwork
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class CategoryDataSource: PageKeyedDataSource<Long, CategoryItem>() {

    override fun loadInitial(
            params: LoadInitialParams<Long>, callback: LoadInitialCallback<Long, CategoryItem>) {
        ConfigNetwork.getRetrofit().getCategories(1, params.requestedLoadSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t ->
                    t?.let { callback.onResult(it, null, 2L) }
                }, {})
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, CategoryItem>) {
        ConfigNetwork.getRetrofit().getCategories(params.key, params.requestedLoadSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t ->
                    t?.let { callback.onResult(it, params.key + 1) }
                }, {})
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, CategoryItem>) {
    }

}