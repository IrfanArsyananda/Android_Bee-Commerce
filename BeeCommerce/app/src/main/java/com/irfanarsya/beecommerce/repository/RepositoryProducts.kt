package com.irfanarsya.beecommerce.repository

import com.irfanarsya.beecommerce.model.ResponseDetailProduct
import com.irfanarsya.beecommerce.model.ResponseGetCart
import com.irfanarsya.beecommerce.model.action.ResponseAddOrder
import com.irfanarsya.beecommerce.model.action.ResponseAddToCart
import com.irfanarsya.beecommerce.model.action.ResponseDeleteCartItem
import com.irfanarsya.beecommerce.model.action.ResponseEditCart
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

    fun getCarts(user_id: Int, responseHandler: (ResponseGetCart)->Unit, errorHandler: (Throwable)->Unit){
        ConfigNetwork.getRetrofit().getCart(user_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            },{
                errorHandler(it)
            })
    }

    fun insertCarts(pro_id: Int, user_id: Int, qty: Int,
                    responseHandler: (ResponseAddToCart)->Unit, errorHandler: (Throwable)->Unit){
        ConfigNetwork.getRetrofit().addToCart(pro_id, user_id, qty)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            },{
                errorHandler(it)
            })
    }

    fun updateCarts(user_id: Int, new_qty: Int, cart_id: Int,
                    responseHandler: (ResponseEditCart)->Unit, errorHandler: (Throwable)->Unit){
        ConfigNetwork.getRetrofit().updateCart(user_id, new_qty, cart_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            },{
                errorHandler(it)
            })
    }

    fun deleteCart(cart_id: Int, responseHandler: (ResponseDeleteCartItem)->Unit, errorHandler: (Throwable)->Unit){
        ConfigNetwork.getRetrofit().deleteCart(cart_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            },{
                errorHandler(it)
            })
    }

    fun addOrder(user_id: Int, responseHandler: (ResponseAddOrder)->Unit, errorHandler: (Throwable)->Unit){
        ConfigNetwork.getRetrofit().addOrder(user_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            },{
                errorHandler(it)
            })
    }

}