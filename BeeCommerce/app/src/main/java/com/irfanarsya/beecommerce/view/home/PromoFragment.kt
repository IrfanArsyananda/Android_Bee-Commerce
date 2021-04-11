package com.irfanarsya.beecommerce.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import com.irfanarsya.beecommerce.R
import com.irfanarsya.beecommerce.adapter.HomeAdapter
import com.irfanarsya.beecommerce.model.DataItem
import com.irfanarsya.beecommerce.viewModel.ViewModelPromo
import kotlinx.android.synthetic.main.fragment_promo.*

class PromoFragment : Fragment() {

    private var viewModel : ViewModelPromo? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_promo, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ViewModelPromo::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        attachObserve()
        viewModel?.getPromo()

    }

    private fun attachObserve() {
        viewModel?.promoData?.observe(viewLifecycleOwner, Observer { showPromo(it) })
    }

    private fun showPromo(it: PagedList<DataItem>?) {
        val adapter = HomeAdapter()
        adapter.submitList(it)
        listPromo.adapter = adapter
        if (it != null) {
            showLoading(false)
        }else{
            showLoading(true)
        }
    }
    private fun showLoading(state: Boolean) {
        if (state) {
            progressPromo.visibility = View.VISIBLE
        } else {
            progressPromo.visibility = View.GONE
        }
    }

}