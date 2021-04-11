package com.irfanarsya.beecommerce.view.shipping

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.irfanarsya.beecommerce.R
import com.irfanarsya.beecommerce.adapter.ShippingAdapter
import com.irfanarsya.beecommerce.model.DatasItem
import com.irfanarsya.beecommerce.model.ShippingAddressItem
import com.irfanarsya.beecommerce.viewModel.ViewModelShipping
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_shipping.*

class ShippingFragment : Fragment() {

    private var viewModel : ViewModelShipping? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shipping, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ViewModelShipping::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        attachObserve()
        viewModel?.getProfil("47")

    }

    private fun attachObserve() {
        viewModel?.onSuccessGetShip?.observe(viewLifecycleOwner, Observer { showShipping(it?.datas) })
        viewModel?.onErrorGetShip?.observe(viewLifecycleOwner, Observer { showError(it) })
        viewModel?.isLoading?.observe(viewLifecycleOwner, Observer { showLoading(it) })
    }

    private fun showLoading(it: Boolean?) {
        if (it == true) {
            progressShip.visibility = View.VISIBLE
        } else {
            progressShip.visibility = View.GONE
        }
    }

    private fun showError(it: Throwable?) {
        Toast.makeText(context, it?.message, Toast.LENGTH_SHORT).show()
    }

    private fun showShipping(it: List<DatasItem?>?) {

        tvAlamatU.text = "Alamat : ${it?.get(0)?.selectedShippingAddress}"
        tvCityU.text = "Kota : ${it?.get(0)?.selectedShippingCity}"
        tvProvinceU.text = "Provinsi : ${it?.get(0)?.selectedShippingProvince}"
        tvZipCodeU.text = "Kode Pos : ${it?.get(0)?.selectedShippingZipCode}"

        val adapter = ShippingAdapter(it?.get(0)?.shippingAddress as List<ShippingAddressItem>?, object : ShippingAdapter.OnClickListener{
            override fun edit(item: ShippingAddressItem?) {

            }

        } )
        listShipping.adapter = adapter
    }

}