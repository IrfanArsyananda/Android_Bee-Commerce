package com.irfanarsya.beecommerce.view.detailProduct

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.irfanarsya.beecommerce.R
import com.irfanarsya.beecommerce.model.ResponseDetailProduct
import com.irfanarsya.beecommerce.network.Constant
import com.irfanarsya.beecommerce.viewModel.ViewModelDetail
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.item_home.view.*

class DetailActivity : AppCompatActivity() {

    lateinit var viewModel: ViewModelDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id = intent.getStringExtra("id")

        viewModel = ViewModelProviders.of(this).get(ViewModelDetail::class.java)

        attachObserve()
        if (id != null) {
            viewModel.getDetail(id)
        }
    }

    private fun attachObserve() {
        viewModel.onSuccess.observe(this, Observer { showDetail(it) })
        viewModel.onError.observe(this, Observer { showEror(it) })
        viewModel.isLoading.observe(this, Observer { showLoading(it) })
    }

    private fun showDetail(it: ResponseDetailProduct?) {
        detailNameProduct.setText(it?.data?.title.toString())
        detailCategoryProduct.setText(it?.data?.category?.title.toString())
        detailPriceProduct.setText("Rp. ${it?.data?.price},-")
        detailDeskripsiProduct.setText(it?.data?.description.toString())

        val name = it?.data?.productPhotos?.get(1)?.fileName
        val urlEnd = Constant.BASE_IMG_PRO + "$name"
        Glide.with(this).load(urlEnd).into(detailImageProduct)
    }

    private fun showEror(it: Throwable?) {
        Toast.makeText(applicationContext, it?.message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(it: Boolean?) {
        if (it == true) {
            progressD.visibility = View.VISIBLE
        } else {
            progressD.visibility = View.GONE
        }
    }
}