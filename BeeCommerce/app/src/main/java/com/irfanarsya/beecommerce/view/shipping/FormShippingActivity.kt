package com.irfanarsya.beecommerce.view.shipping

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.irfanarsya.beecommerce.R
import com.irfanarsya.beecommerce.model.action.ResponseEditShipping
import com.irfanarsya.beecommerce.view.home.MainActivity
import com.irfanarsya.beecommerce.viewModel.ViewModelFormShipping
import kotlinx.android.synthetic.main.activity_form_shipping.*
import kotlinx.android.synthetic.main.activity_form_shipping.btnBatal
import kotlinx.android.synthetic.main.activity_form_shipping.btnUbah

class FormShippingActivity : AppCompatActivity() {

    lateinit var viewModel : ViewModelFormShipping
    private var isMainAdd: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_shipping)

        viewModel = ViewModelProviders.of(this).get(ViewModelFormShipping::class.java)

        val id = intent.getStringExtra("id")
        val userId = intent.getStringExtra("uid")
        val title = intent.getStringExtra("title")
        val city = intent.getStringExtra("city")
        val province = intent.getStringExtra("pro")
        val address = intent.getStringExtra("add")
        val zipCode = intent.getStringExtra("zcode")
        val isMain = intent.getStringExtra("ismain")

        if (id != null) {
            etJudul.setText(title)
            etProv.setText(province)
            etKota.setText(city)
            etAlamat.setText(address)
            etKodePos.setText(zipCode)
            isMainAdd = isMain?.toInt()
            supportActionBar?.title = "Ubah Alamat"
            btnSimpan.visibility = View.GONE
        } else {
            supportActionBar?.title = "Tambah Alamat"
            btnUbah.visibility = View.GONE
        }

        if (isMainAdd == 1){
            icYes.visibility  = View.VISIBLE
        }else{
            icNo.visibility  = View.VISIBLE
        }

        btnSimpan.setOnClickListener {
            val jdl = etJudul.text.toString()
            val pro = etProv.text.toString()
            val kt = etKota.text.toString()
            val add = etAlamat.text.toString()
            val zip = etKodePos.text.toString()
            viewModel.addShip(jdl, kt, pro, add, zip, isMainAdd.toString(), userId?:"")
        }

        btnUbah.setOnClickListener {
            val jdl = etJudul.text.toString()
            val pro = etProv.text.toString()
            val kt = etKota.text.toString()
            val add = etAlamat.text.toString()
            val zip = etKodePos.text.toString()
            viewModel.editShip(jdl, kt, pro, add, zip, isMainAdd.toString(), userId?:"", id?:"0")
        }

        btnBatal.setOnClickListener {
            finish()
        }

        attachObserve()

    }

    private fun attachObserve() {
        viewModel.onSuccessEditShip.observe(this, Observer { showSuccess(it) })
        viewModel.onErrorEditShip.observe(this, Observer { showError(it) })
        viewModel.isLoading.observe(this, Observer { showLoading(it) })
        viewModel.isKosong.observe(this, Observer { showEmpty(it) })
        viewModel.onSuccessAddShip.observe(this, Observer { showSuccessAdd(it) })
        viewModel.onErrorAddShip.observe(this, Observer { showErrorAdd(it) })
    }

    private fun showErrorAdd(it: Throwable?) {
        Toast.makeText(this, it?.message, Toast.LENGTH_SHORT).show()
    }

    private fun showSuccessAdd(it: ResponseEditShipping?) {
        Toast.makeText(this, "Data berhasil disimpan !", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun showEmpty(it: Boolean?) {
        if (it == true){
            Toast.makeText(this, "Semua kolom harus diisi !", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showLoading(it: Boolean?) {
        if (it == true){
            progressFormShipping.visibility = View.VISIBLE
        }else{
            progressFormShipping.visibility = View.GONE
        }
    }

    private fun showError(it: Throwable?) {
        Toast.makeText(this, it?.message, Toast.LENGTH_SHORT).show()
    }

    private fun showSuccess(it: ResponseEditShipping?) {
        Toast.makeText(this, "Data berhasil diupdate !", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    fun onCheckboxClicked(view: View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked

            when (view.id) {
                R.id.etCheckBox -> {
                    if (checked) {
                        isMainAdd = 1
                    } else {
                        isMainAdd = 0
                    }
                }
            }
        }
    }
}