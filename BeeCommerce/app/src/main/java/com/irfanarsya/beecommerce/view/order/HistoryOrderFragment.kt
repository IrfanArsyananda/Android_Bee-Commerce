package com.irfanarsya.beecommerce.view.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.paging.PagedList
import com.irfanarsya.beecommerce.R
import com.irfanarsya.beecommerce.adapter.OrdersAdapter
import com.irfanarsya.beecommerce.helper.SessionManager
import com.irfanarsya.beecommerce.model.DataItemGO
import com.irfanarsya.beecommerce.viewModel.ViewModelOrders
import kotlinx.android.synthetic.main.fragment_history_order.*

class HistoryOrderFragment : Fragment() {

    lateinit var navController: NavController
    private var viewModel: ViewModelOrders? = null
    private var session : SessionManager? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_history_order, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ViewModelOrders::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        session = context?.let { SessionManager(it) }

        navController = Navigation.findNavController(view)
        //   val userId = arguments?.getString("uid")

        attachObserve()
//        viewModel?.getOrders(47)
        viewModel?.setOrderId(session?.id?.toInt() ?: 47)?.observe(viewLifecycleOwner, Observer { showOrders(it) })
//        viewModel?.userId = 47

    }

    private fun attachObserve() {
        //   viewModel?.getOrders()?.observe(viewLifecycleOwner, Observer { showOrders(it) })
//        viewModel?.setOrderId(47).observe(viewLifecycleOwner, Observer { showOrders(it) })
    }

    private fun showOrders(it: PagedList<DataItemGO>?) {
        val adapter = OrdersAdapter(object : OrdersAdapter.OnClickListener{
            override fun detail(item: DataItemGO?) {
//                val bundle = bundleOf(
//                    "item2" to item,
//                )
//                navController.navigate(R.id.action_historyOrderFragment_to_ordersProductFragment, bundle)
            }
        })
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