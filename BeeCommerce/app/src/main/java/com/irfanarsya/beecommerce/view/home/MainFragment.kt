package com.irfanarsya.beecommerce.view.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import com.irfanarsya.beecommerce.R
import com.irfanarsya.beecommerce.adapter.HomeAdapter
import com.irfanarsya.beecommerce.model.DataItem
import com.irfanarsya.beecommerce.viewModel.ViewModelHome
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private var viewModel : ViewModelHome? = null

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

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        attachObserve()
        viewModel?.getHomeProduts()

    }

    private fun attachObserve() {
        viewModel?.homeData?.observe(viewLifecycleOwner, Observer { showHomeProduts(it) })
    }

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