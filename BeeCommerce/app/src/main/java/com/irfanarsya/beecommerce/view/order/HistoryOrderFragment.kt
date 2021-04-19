package com.irfanarsya.beecommerce.view.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import com.irfanarsya.beecommerce.R
import com.irfanarsya.beecommerce.adapter.OrdersAdapter
import com.irfanarsya.beecommerce.model.DataItemGO
import com.irfanarsya.beecommerce.viewModel.ViewModelOrders
import kotlinx.android.synthetic.main.fragment_history_order.*

class HistoryOrderFragment : Fragment() {

    private var viewModel: ViewModelOrders? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_history_order, container, false)

        viewModel = ViewModelProviders.of(this).get(ViewModelOrders::class.java)
        val userId = arguments?.getString("uid")

        attachObserve()
//        viewModel?.getOrders(47)
        viewModel?.setOrderId(47)
//        viewModel?.userId = 47

        return root
    }

    private fun attachObserve() {
//        viewModel?.ordersData?.observe(viewLifecycleOwner, Observer { showOrders(it) })
//        viewModel?.setOrderId(47).observe(viewLifecycleOwner, Observer { showOrders(it) })
    }

    private fun showOrders(it: PagedList<DataItemGO>?) {
                val adapter = OrdersAdapter()
        adapter.submitList(it)
        listOrders.adapter = adapter
        if (it != null) {
            showLoading(false)
        }else{
            showLoading(true)
        }
    }

    private fun showLoading(state: Boolean?) {
        if (state == true) {
            progressOrders.visibility = View.VISIBLE
        } else {
            progressOrders.visibility = View.GONE
        }
    }

}