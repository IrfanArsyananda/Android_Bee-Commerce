package com.irfanarsya.beecommerce.view.home

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.irfanarsya.beecommerce.R
import com.irfanarsya.beecommerce.helper.SessionManager
import com.irfanarsya.beecommerce.model.ResponseGetProfile
import com.irfanarsya.beecommerce.network.Constant
import com.irfanarsya.beecommerce.view.LoginActivity
import com.irfanarsya.beecommerce.view.editProfil.EditFotoActivity
import com.irfanarsya.beecommerce.viewModel.ViewModelProfile
import kotlinx.android.synthetic.main.fragment_profil.*

class ProfilFragment : Fragment() {

    lateinit var navController: NavController
    private var viewModel : ViewModelProfile? = null
    private var userId : String? = null
    private var email : String? = null
    private var fName : String? = null
    private var lName : String? = null
    private var urlFoto : String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ViewModelProfile::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        val session = context?.let { SessionManager(it) }
        val id = session?.id
        attachObserve()
        viewModel?.getProfil(id.toString())

        btnEditFoto.setOnClickListener {
            val intent = Intent(context, EditFotoActivity::class.java)
            intent.putExtra("userId", userId)
            intent.putExtra("urlFoto", urlFoto)
            context?.startActivity(intent)
        }

        btnEditProfile.setOnClickListener {
//            val intent = Intent(context, FormEditProfil::class.java)
//            intent.putExtra("uId", userId)
//            intent.putExtra("fName", fName)
//            intent.putExtra("lName", lName)
//            intent.putExtra("email", email)
//            context?.startActivity(intent)
            val bundle = bundleOf(
                "uId" to userId,
                "fName" to fName,
                "lName" to lName,
                "email" to email,
            )
            navController.navigate(R.id.action_profilFragment_to_editProfilFragment, bundle)
        }

        btnHistory.setOnClickListener {
            val bundle = bundleOf(
                "uid" to userId
            )
            navController.navigate(R.id.action_profilFragment_to_historyOrderFragment, bundle)
        }

        btnShipping.setOnClickListener {
            val bundle = bundleOf(
                "id" to userId
            )
            navController.navigate(R.id.action_profilFragment_to_shippingFragment, bundle)

        }

        btnHistorySearch.setOnClickListener {
            val bundle = bundleOf(
                "uid" to userId
            )
            navController.navigate(R.id.action_profilFragment_to_historySearchFragment, bundle)
        }

        btnLogOut.setOnClickListener {
            AlertDialog.Builder(context).apply {
                setTitle("Keluar")
                setMessage("Apakah anda yakin?")
                setPositiveButton("Ya") { dialog, which ->
                    confirmOut()
                    dialog.dismiss()
                }
                setNegativeButton("Batal") { dialog, which ->
                    dialog.dismiss()
                }
            }.show()
        }

    }

    private fun confirmOut() {
        val session = context?.let { SessionManager(it) }
        val intent = Intent(context, LoginActivity::class.java)
        session?.logOut()
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        activity?.finish()
    }

    private fun attachObserve() {
        viewModel?.onSuccessGetProfil?.observe(viewLifecycleOwner, Observer { showProfil(it) })
        viewModel?.onErrorGetProfil?.observe(viewLifecycleOwner, Observer { showError(it) })
        viewModel?.isLoading?.observe(viewLifecycleOwner, Observer { showLoading(it) })
    }

    private fun showLoading(it: Boolean?) {
        if (it == true) {
            progressProfile.visibility = View.VISIBLE
        } else {
            progressProfile.visibility = View.GONE
        }
    }

    private fun showError(it: Throwable?) {
        Toast.makeText(context, it?.message, Toast.LENGTH_SHORT).show()
    }

    private fun showProfil(it: ResponseGetProfile?) {
        email = it?.datas?.get(0)?.email
        fName = it?.datas?.get(0)?.firstName
        lName = it?.datas?.get(0)?.lastName
        userId = it?.datas?.get(0)?.id.toString()
        urlFoto = it?.datas?.get(0)?.photo

        tvUserId.setText("User ID : $userId")
        tvEmail.setText("Email : $email")
        tvNama.setText("Nama : $fName $lName")

        val name = it?.datas?.get(0)?.photo
        val urlEnd = Constant.BASE_IMG_USER + "$name"
        Glide.with(this).load(urlEnd).into(imageProfile)

    }

}