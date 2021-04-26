package com.irfanarsya.beecommerce.view.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import com.irfanarsya.beecommerce.R
import com.irfanarsya.beecommerce.adapter.HistoryAdapter
import com.irfanarsya.beecommerce.adapter.HomeAdapter
import com.irfanarsya.beecommerce.local.DatabaseHistory
import com.irfanarsya.beecommerce.local.History
import com.irfanarsya.beecommerce.model.DataItem
import com.irfanarsya.beecommerce.viewModel.ViewModelHome
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_history_search.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import java.text.SimpleDateFormat
import java.util.*

class MainFragment : Fragment() {

    private var viewModel : ViewModelHome? = null
    private var historyDatabase: DatabaseHistory? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ViewModelHome::class.java)
        historyDatabase = context?.let { DatabaseHistory.getInstance(it) }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        attachObserve()
        viewModel?.getHomeProduts()

        btnSearch.setOnClickListener {
            val key = etSearch.text.toString()
//            viewModel?.getSearchProduts()
            Toast.makeText(context, key, Toast.LENGTH_SHORT).show()
            viewModel?.searchHome(key)?.observe(viewLifecycleOwner, Observer { showHomeProduts(it) })
            if (key.isNotEmpty()){
                insert(History(null, key,getDate()))
            }
        }

    }

    private fun insert(history: History) {
        Observable.fromCallable { historyDatabase?.historyDao()?.insertHistory(history) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                           if (it == null){
                               Toast.makeText(context, "null", Toast.LENGTH_SHORT).show()
                           }else{
                               Toast.makeText(context, "done", Toast.LENGTH_SHORT).show()
                           }
                },{
                    Toast.makeText(context, it?.message, Toast.LENGTH_SHORT).show()
                })
    }

    private fun getDate(): String? {
        val date = Calendar.getInstance().time
        val sdf = SimpleDateFormat("dd/MM/yyyy - hh:mm:ss")
        val formatDate = sdf.format(date)
        return formatDate
    }

    private fun attachObserve() {
        viewModel?.homeData?.observe(viewLifecycleOwner, Observer { showHomeProduts(it) })
//        viewModel?.searchData?.observe(viewLifecycleOwner, Observer { showSearch(it) })
        viewModel?.onSuccessInsert?.observe(viewLifecycleOwner, Observer { showHistory(it) })
        viewModel?.onErrorInsert?.observe(viewLifecycleOwner, Observer { showError(it) })
        viewModel?.isLoading?.observe(viewLifecycleOwner, Observer { showLoading(it) })
    }

    private fun showHistory(it: History?) {
        Toast.makeText(context, "Cari...", Toast.LENGTH_SHORT).show()
    }

    private fun showError(it: Throwable?) {
        Toast.makeText(context, it?.message, Toast.LENGTH_SHORT).show()
    }

//    private fun showSearch(it: PagedList<DataItem>?) {
//        val adapter = HomeAdapter()
//        adapter.submitList(it)
//        listHome.adapter = adapter
//        if (it != null) {
//            showLoading(false)
//        }else{
//            showLoading(true)
//        }
//    }

    private fun showHomeProduts(it: PagedList<DataItem>?) {
        val adapter = HomeAdapter()
        adapter.submitList(it)
        listHome.adapter = adapter
        if (it != null) {
            showLoading(false)
        }else{
            showLoading(true)
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressHome.visibility = View.VISIBLE
        } else {
            progressHome.visibility = View.GONE
        }
    }

}