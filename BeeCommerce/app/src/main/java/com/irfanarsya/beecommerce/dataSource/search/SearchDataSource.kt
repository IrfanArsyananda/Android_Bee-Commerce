package com.irfanarsya.beecommerce.dataSource.search
//
//import androidx.paging.PageKeyedDataSource
//import com.irfanarsya.beecommerce.model.DataItem
//import com.irfanarsya.beecommerce.network.ConfigNetwork
//import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
//import io.reactivex.rxjava3.schedulers.Schedulers
//
//class SearchDataSource: PageKeyedDataSource<Long, DataItem>() {
//
//    var q: String = "jam"
//
//    override fun loadInitial(
//            params: LoadInitialParams<Long>, callback: LoadInitialCallback<Long, DataItem>
//    ) {
//        ConfigNetwork.getRetrofit().getHomeProductsByKey(1, params.requestedLoadSize, q)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({ t ->
//                    t.data?.let { callback.onResult(it, null, 2L) }
//                }, {})
//    }
//
//    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, DataItem>) {
//        ConfigNetwork.getRetrofit().getHomeProductsByKey(params.key, params.requestedLoadSize, q)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({ t ->
//                    t.data?.let { callback.onResult(it, params.key + 1) }
//                }, {})
//    }
//
//    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, DataItem>) {
//    }
//
//    fun search(text: String) {
//        q = text
//    }
//
//}