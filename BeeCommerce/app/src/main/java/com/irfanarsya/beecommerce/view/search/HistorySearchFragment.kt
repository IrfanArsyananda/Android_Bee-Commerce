package com.irfanarsya.beecommerce.view.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.irfanarsya.beecommerce.R
import com.irfanarsya.beecommerce.adapter.HistoryAdapter
import com.irfanarsya.beecommerce.local.DatabaseHistory
import com.irfanarsya.beecommerce.local.History
import com.irfanarsya.beecommerce.viewModel.ViewModelHistorySearch
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_history_search.*

class HistorySearchFragment : Fragment() {

    private var historyDatabase: DatabaseHistory? = null
    private var viewModel: ViewModelHistorySearch? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_history_search, container, false)

        viewModel = ViewModelProviders.of(this).get(ViewModelHistorySearch::class.java)
        historyDatabase = context?.let { DatabaseHistory.getInstance(it) }

        attachObserve()
//        viewModel?.showHistory()

        showH()

        return root
    }

    private fun showH() {
        Observable.fromCallable { historyDatabase!!.historyDao().getAllHistory() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val adapter = HistoryAdapter(it)
                    listHistorySearch.adapter = adapter
                }, {
//                    errorHandler(it)
                })
    }

    private fun attachObserve() {
        viewModel?.onSuccessShow?.observe(viewLifecycleOwner, Observer { showHistory(it) })
//        viewModel?.onErrorShow?.observe(viewLifecycleOwner, Observer { showError(it) })
        viewModel?.isLoading?.observe(viewLifecycleOwner, Observer { showLoading(it) })

    }

    private fun showLoading(it: Boolean?) {
        if (it == true) {
            progressHistorySearch.visibility = View.VISIBLE
        } else {
            progressHistorySearch.visibility = View.GONE
        }
    }

    private fun showError(it: Throwable?) {
        Toast.makeText(context, it?.message, Toast.LENGTH_SHORT).show()
    }

    private fun showHistory(it: List<History>) {
        val adapter = HistoryAdapter(it)
        listHistorySearch.adapter = adapter
        Log.d("ipan", it.get(0).keyword.toString())
    }

}