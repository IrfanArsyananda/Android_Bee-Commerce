package com.irfanarsya.beecommerce.repository

import com.irfanarsya.beecommerce.model.*
import com.irfanarsya.beecommerce.model.action.ResponseEditFoto
import com.irfanarsya.beecommerce.model.action.ResponseEditProfil
import com.irfanarsya.beecommerce.model.action.ResponseEditShipping
import com.irfanarsya.beecommerce.network.ConfigNetwork
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.MultipartBody
import okhttp3.RequestBody

class RepositoryUser {

    private var compositeDisposable = CompositeDisposable()

    fun login(email: String, password: String, responseHandler: (ResponseLogin)->Unit, errorHandler: (Throwable)->Unit){
        ConfigNetwork.getRetrofit().login(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            },{
                errorHandler(it)
            })
    }

    fun register(email: String, first_name: String, last_name: String, password: String,
                 responseHandler: (ResponseRegister)->Unit, errorHandler: (Throwable)->Unit){
        ConfigNetwork.getRetrofit().register(email, first_name, last_name, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            },{
                errorHandler(it)
            })

    }

    fun getProfil(userId: String, responseHandler: (ResponseGetProfile)->Unit, errorHandler: (Throwable)->Unit){
        ConfigNetwork.getRetrofit().getProfile(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            },{
                errorHandler(it)
            })
    }

    fun editProfil(first_name: String, last_name: String, userId: String,
                   responseHandler: (ResponseEditProfil)->Unit, errorHandler: (Throwable)->Unit){
        ConfigNetwork.getRetrofit().updateProfil(first_name, last_name, userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            },{
                errorHandler(it)
            })
    }

    fun editFoto(id: RequestBody, foto: MultipartBody.Part, responseHandler: (ResponseEditFoto)->Unit, errorHandler: (Throwable)->Unit){
        compositeDisposable.add(ConfigNetwork.getRetrofit().updateFoto(id, foto)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            },{
                errorHandler(it)
            })
        )
    }

    fun addShipping(judul: String, kota: String, prov: String, add: String, zip: String, isMain: String, u_id: String,
                     responseHandler: (ResponseEditShipping)->Unit, errorHandler: (Throwable)->Unit){
        ConfigNetwork.getRetrofit().addShipping(judul, kota, prov, add, zip, isMain, u_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    responseHandler(it)
                },{
                    errorHandler(it)
                })
    }

    fun editShipping(judul: String, kota: String, prov: String, add: String, zip: String, isMain: String, u_id: String, add_id: String,
                   responseHandler: (ResponseEditShipping)->Unit, errorHandler: (Throwable)->Unit){
        ConfigNetwork.getRetrofit().updateShipping(judul, kota, prov, add, zip, isMain, u_id, add_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    responseHandler(it)
                },{
                    errorHandler(it)
                })
    }

}