package com.irfanarsya.beecommerce.repository

import android.os.Handler
import android.widget.Toast
import com.irfanarsya.beecommerce.local.DatabaseHistory
import com.irfanarsya.beecommerce.local.History
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
class RepositoryLocal {

    private var historyDatabase: DatabaseHistory? = null

    fun insertHistory(item: History
//                      , responseHandler: (History) -> Unit, errorHandler: (Throwable) -> Unit
    ) {
        Observable.fromCallable { historyDatabase?.historyDao()?.insertHistory(item)}
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }

//    fun deleteNote(item: Notes?){
//        Observable.fromCallable { noteDatabase?.notesDao()?.deleteNote(item!!) }
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    Toast.makeText(context, "Note berhasil dihapus!", Toast.LENGTH_SHORT).show()
//                    showNote()
//                }, {
//                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
//                })
//    }

    fun showHistory(responseHandler: (List<History>?) -> Unit) {
        Observable.fromCallable { historyDatabase?.historyDao()?.getAllHistory() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                           responseHandler(it)
                },{})
    }

}