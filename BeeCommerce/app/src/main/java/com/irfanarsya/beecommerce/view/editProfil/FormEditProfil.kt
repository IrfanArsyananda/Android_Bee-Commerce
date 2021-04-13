package com.irfanarsya.beecommerce.view.editProfil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.irfanarsya.beecommerce.R
import com.irfanarsya.beecommerce.model.action.ResponseEditProfil
import com.irfanarsya.beecommerce.view.home.MainActivity
import com.irfanarsya.beecommerce.viewModel.ViewModelEditProfil
import kotlinx.android.synthetic.main.activity_form_edit_profil.*
import kotlinx.android.synthetic.main.activity_form_edit_profil.etEmail

class FormEditProfil : AppCompatActivity() {

    lateinit var viewModel: ViewModelEditProfil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_edit_profil)

        viewModel = ViewModelProviders.of(this).get(ViewModelEditProfil::class.java)

        val userId = intent.getStringExtra("uId")
        val firstName = intent.getStringExtra("fName")
        val lastName = intent.getStringExtra("lName")
        val email = intent.getStringExtra("email")

        etNamaDepan.setText(firstName)
        etNamaBelakang.setText(lastName)
        etEmail.setText(email)

        btnUbah.setOnClickListener {
            val fName = etNamaDepan.text.toString()
            val lName = etNamaBelakang.text.toString()

            if (fName.isEmpty()) {
                etNamaDepan.setError("Kolom harus diisi!")
            } else if (lName.isEmpty()) {
                etNamaBelakang.setError("Kolom harus diisi!")
            } else {
                viewModel.editProfil(fName, lName, userId.toString())
            }
        }

        btnBatal.setOnClickListener {
            finish()
        }

        attachObserve()

    }

    private fun attachObserve() {
        viewModel.onSuccessEditProfil.observe(this, Observer { setUpdate(it) })
        viewModel.onErrorEditProfil.observe(this, Observer { showError(it) })
        viewModel.isLoading.observe(this, Observer { showLoading(it) })
    }

    private fun showError(it: Throwable?) {
        Toast.makeText(applicationContext, it?.message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(it: Boolean?) {
        if (it == true){
            progressEdit.visibility = View.VISIBLE
        }else{
            progressEdit.visibility = View.GONE
        }
    }

    private fun setUpdate(it: ResponseEditProfil?) {
        Toast.makeText(this, "Data berhasil diupdate !", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}