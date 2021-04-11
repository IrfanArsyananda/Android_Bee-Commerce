package com.irfanarsya.beecommerce.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.irfanarsya.beecommerce.view.home.MainActivity
import com.irfanarsya.beecommerce.R
import com.irfanarsya.beecommerce.helper.SessionManager
import com.irfanarsya.beecommerce.model.ResponseLogin
import com.irfanarsya.beecommerce.viewModel.ViewModelLogin
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var viewModel: ViewModelLogin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProviders.of(this).get(ViewModelLogin::class.java)

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (email.isEmpty()){
                etEmail.setError("Email harus diisi!")
            } else if (password.isEmpty()){
                etPassword.setError("Password harus diisi!")
            }else{
                viewModel.getLogin(email, password)
            }
        }

        btnToSign.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        attachObserve()

    }

    private fun attachObserve() {
        viewModel.onSuccessLogin.observe(this, Observer { getLogin(it) })
        viewModel.onErrorLogin.observe(this, Observer { showError(it) })
        viewModel.isLoading.observe(this, Observer { showLoading(it) })
    }

    private fun showLoading(it: Boolean?) {
        if (it == true){
            progressLogin.visibility = View.VISIBLE
        }else{
            progressLogin.visibility = View.GONE
        }
    }

    private fun showError(it: Throwable?) {
        Toast.makeText(applicationContext, it?.message, Toast.LENGTH_SHORT).show()
    }

    private fun getLogin(it: ResponseLogin?) {
        val item = it?.data
        if (it?.isSuccess == true){
            val session = SessionManager(this)
            session.id = item?.id.toString()
            session.nama = item?.lastName
            session.email = item?.email
            session.login = true
            Toast.makeText(this, "Selamat Datang "+session.nama+" !", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }else{
            Toast.makeText(this, it?.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }

}