package com.irfanarsya.beecommerce.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.irfanarsya.beecommerce.R
import com.irfanarsya.beecommerce.model.ResponseRegister
import com.irfanarsya.beecommerce.viewModel.ViewModelRegister
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    lateinit var viewModel: ViewModelRegister

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        viewModel = ViewModelProviders.of(this).get(ViewModelRegister::class.java)

        btnRegister.setOnClickListener {
            val namaD = etNamaDepan.text.toString()
            val namaB = etNamaBelakang.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val confirmPassword = etKonfirmPassword.text.toString()

            viewModel.register(namaD, namaB, email, password, confirmPassword)
        }

        attachObserve()

    }

    private fun attachObserve() {
        viewModel.onSuccessRegister.observe(this, Observer { setRegister(it) })
        viewModel.onErrorRegister.observe(this, Observer { showError(it) })
        viewModel.isLoading.observe(this, Observer { showLoading(it) })
    }

    private fun setRegister(it: ResponseRegister?) {
        Toast.makeText(this, "Registrasi berhasil !", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun showError(it: String?) {
        if (it == "KOSONG"){
            Toast.makeText(this, "Semua data harus diisi !", Toast.LENGTH_SHORT).show()
        }else if (it == "NOMATCH"){
            Toast.makeText(this, "Password tidak cocok !", Toast.LENGTH_SHORT).show()
        }else if (it == "LESS6"){
            Toast.makeText(this, "Password minimal 6 karakter !", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun showLoading(it: Boolean?) {
        if (it == true){
            progressRegister.visibility = View.VISIBLE
        }else{
            progressRegister.visibility = View.GONE
        }
    }
}