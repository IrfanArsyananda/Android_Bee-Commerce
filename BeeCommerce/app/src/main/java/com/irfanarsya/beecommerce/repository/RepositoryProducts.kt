package com.irfanarsya.beecommerce.repository

import com.irfanarsya.beecommerce.model.ResponseDetailProduct
import com.irfanarsya.beecommerce.network.ConfigNetwork
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class RepositoryProducts {

    fun getDetailProduct(product_id: Int, responseHandler: (ResponseDetailProduct)->Unit, errorHandler: (Throwable)->Unit){
        ConfigNetwork.getRetrofit().getDetailProduct(product_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    responseHandler(it)
                },{
                    errorHandler(it)
                })
    }

}