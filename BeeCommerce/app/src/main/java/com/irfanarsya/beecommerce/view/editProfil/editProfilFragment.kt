package com.irfanarsya.beecommerce.view.editProfil

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.irfanarsya.beecommerce.R
import com.irfanarsya.beecommerce.model.action.ResponseEditProfil
import com.irfanarsya.beecommerce.viewModel.ViewModelEditProfil
import kotlinx.android.synthetic.main.fragment_edit_profil.*

class editProfilFragment : Fragment() {

    private var viewModel: ViewModelEditProfil? = null
    lateinit var navController: NavController
    private var userId: String? = null
    private var fName: String? = null
    private var lName: String? = null
    private var email: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_profil, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ViewModelEditProfil::class.java)

        userId = arguments?.getString("uId")
        fName = arguments?.getString("fName")
        lName = arguments?.getString("lName")
        email = arguments?.getString("email")

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val userId = intent.getStringExtra("uId")
//        val firstName = intent.getStringExtra("fName")
//        val lastName = intent.getStringExtra("lName")
//        val email = intent.getStringExtra("email")

        navController = Navigation.findNavController(view)

        etUserId.setText(userId)
        etNamaDepan.setText(fName)
        etNamaBelakang.setText(lName)
        etEmail.setText(email)

        btnUbah.setOnClickListener {
            val fName = etNamaDepan.text.toString()
            val lName = etNamaBelakang.text.toString()

            if (fName.isEmpty()) {
                etNamaDepan.setError("Kolom harus diisi!")
            } else if (lName.isEmpty()) {
                etNamaBelakang.setError("Kolom harus diisi!")
            } else {
                viewModel?.editProfil(fName, lName, userId.toString())
            }
        }

        btnBatal.setOnClickListener {
            activity?.onBackPressed()
        }

        attachObserve()

    }

    private fun attachObserve() {
        viewModel?.onSuccessEditProfil?.observe(viewLifecycleOwner, Observer { setUpdate(it) })
        viewModel?.onErrorEditProfil?.observe(viewLifecycleOwner, Observer { showError(it) })
        viewModel?.isLoading?.observe(viewLifecycleOwner, Observer { showLoading(it) })
    }

    private fun showError(it: Throwable?) {
        Toast.makeText(context, it?.message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(it: Boolean?) {
        if (it == true){
            progressEdit.visibility = View.VISIBLE
        }else{
            progressEdit.visibility = View.GONE
        }
    }

    private fun setUpdate(it: ResponseEditProfil?) {
        Toast.makeText(context, "Data berhasil diupdate !", Toast.LENGTH_SHORT).show()
        navController.navigate(R.id.action_editProfilFragment_to_profilFragment)
    }

}