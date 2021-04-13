package com.irfanarsya.beecommerce.view.detailProduct

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.irfanarsya.beecommerce.R
import com.irfanarsya.beecommerce.helper.SessionManager
import com.irfanarsya.beecommerce.model.ResponseDetailProduct
import com.irfanarsya.beecommerce.model.action.ResponseAddToCart
import com.irfanarsya.beecommerce.network.Constant
import com.irfanarsya.beecommerce.viewModel.ViewModelDetail
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.dialog_form_qty.*
import kotlinx.android.synthetic.main.dialog_form_qty.view.*

class DetailActivity : AppCompatActivity() {

    lateinit var viewModel: ViewModelDetail
    private var dialogView: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id = intent.getStringExtra("id")
        val myC = intent.getStringExtra("cartId")
        val session = SessionManager(this)
        val userId = session.id

        viewModel = ViewModelProviders.of(this).get(ViewModelDetail::class.java)

        attachObserve()
        if (id != null) {
            viewModel.getDetail(id)
        }

        if (myC != null){
            supportActionBar?.setTitle("Detail Cart $myC")
            btnTambahKeranjang.visibility = View.GONE
        }

        btnTambahKeranjang.setOnClickListener {
            showDialogQty(id.toString(), userId.toString())
        }

    }

    private fun showDialogQty(id: String, userId: String) {
        val dialog = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.dialog_form_qty, null)
        dialog.setView(view)

        view.btnCancel.setOnClickListener {
            dialogView?.dismiss()
        }

        view.btnAddToCart.setOnClickListener {
            val qty1 = view.etQty.text.toString()
            if (qty1.isEmpty()){
                view.etQty.error = "Jumlah tidak boleh kosong !"
            }else if (qty1 == "0"){
                view.etQty.error = "Tidak boleh 0"
            }else if (qty1.length > 2){
                view.etQty.error = "Jumlah terlalu banyak !"
            }else{
                addToCart(id,userId,qty1)
                dialogView?.dismiss()
            }
        }
        dialogView = dialog.create()
        dialogView?.show()
    }

    private fun addToCart(proId: String, userId: String, qty:String) {
        viewModel.insertToCart(proId, userId, qty)
    }

    private fun attachObserve() {
        viewModel.onSuccess.observe(this, Observer { showDetail(it) })
        viewModel.onError.observe(this, Observer { showEror(it) })
        viewModel.isLoading.observe(this, Observer { showLoading(it) })
        viewModel.onSuccessAddCart.observe(this, Observer { showSuccessAdd(it) })
        viewModel.onErrorAddCart.observe(this, Observer { showEror(it) })
    }

    private fun showSuccessAdd(it: ResponseAddToCart?) {
        Toast.makeText(this, "Berhasil menambah keranjang !",Toast.LENGTH_SHORT).show()
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