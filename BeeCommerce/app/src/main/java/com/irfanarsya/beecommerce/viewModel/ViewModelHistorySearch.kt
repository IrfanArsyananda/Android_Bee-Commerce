package com.irfanarsya.beecommerce.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irfanarsya.beecommerce.local.History
import com.irfanarsya.beecommerce.repository.RepositoryLocal
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class ViewModelHistorySearch : ViewModel() {

    val repoLocal = RepositoryLocal()

    var onSuccessInsert = MutableLiveData<History>()
    var onErrorInsert = MutableLiveData<Throwable>()
    var isLoading = MutableLiveData<Boolean>()

//    fun insertHistory(item: History) {
//        isLoading.value = true
//
//        repoLocal.insertHistory(item, {
//            onSuccessInsert.value = it
//            isLoading.value = false
//        }, {
//            onErrorInsert.value = it
//            isLoading.value = false
//        })
//
//    }

    var onSuccessShow = MutableLiveData<List<History>>()
    var onErrorShow = MutableLiveData<Throwable>()

    fun showHistory(){
        isLoading.value = true

//        Observable.fromCallable { historyDatabase!!.historyDao().getAllHistory() }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                responseHandler(it as MutableList<History>)
//            },{
//                errorHandler(it)
//            })

        repoLocal.showHistory({
            onSuccessShow.value = it
            isLoading.value = false
        },{
            onErrorShow.value = it
            isLoading.value = false
        })

    }

}