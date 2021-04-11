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
import com.irfanarsya.beecommerce.adapter.CategoryAdapter
import com.irfanarsya.beecommerce.model.CategoryItem
import com.irfanarsya.beecommerce.viewModel.ViewModelCategory
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.android.synthetic.main.fragment_main.*

class CategoryFragment : Fragment() {

    private var viewModel : ViewModelCategory? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ViewModelCategory::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        attachObserve()
        viewModel?.getCategory()

    }

    private fun attachObserve() {
        viewModel?.categoryData?.observe(viewLifecycleOwner, Observer { showCategory(it) })
    }

    private fun showCategory(it: PagedList<CategoryItem>?) {
        val adapter = CategoryAdapter()
        adapter.submitList(it)
        listCategory.adapter = adapter
    }

}