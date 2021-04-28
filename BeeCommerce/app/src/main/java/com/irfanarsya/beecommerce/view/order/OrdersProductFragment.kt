package com.irfanarsya.beecommerce.view.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.irfanarsya.beecommerce.R
import com.irfanarsya.beecommerce.model.ProductsItem

class OrdersProductFragment : Fragment() {

    private var item: List<ProductsItem>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_orders_product, container, false)

//        item = arguments?.getParcelableArray("item2").

        return root
    }

}