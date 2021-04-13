package com.irfanarsya.beecommerce.view.home

import android.app.AlertDialog
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
import com.irfanarsya.beecommerce.adapter.CartsAdapter
import com.irfanarsya.beecommerce.helper.SessionManager
import com.irfanarsya.beecommerce.model.DataItemC
import com.irfanarsya.beecommerce.model.ResponseGetCart
import com.irfanarsya.beecommerce.model.action.ResponseDeleteCartItem
import com.irfanarsya.beecommerce.view.detailProduct.DetailActivity
import com.irfanarsya.beecommerce.viewModel.ViewModelCarts
import kotlinx.android.synthetic.main.fragment_cart.*

class CartFragment : Fragment() {

    lateinit var viewModel : ViewModelCarts
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ViewModelCarts::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        val session = context?.let { SessionManager(it) }
        val user_id = session?.id

        attachObserve()
        viewModel.getCarts(user_id.toString())

        btnCheckout.setOnClickListener {
//            showDialogCheckout()
        }

    }

//    private fun showDialogCheckout() {
//    }

    private fun attachObserve() {
        viewModel.onSuccessGetCarts.observe(viewLifecycleOwner, Observer { showCarts(it) })
        viewModel.onErrorGetCarts.observe(viewLifecycleOwner, Observer { showError(it) })
        viewModel.isLoading.observe(viewLifecycleOwner, Observer { showLoading(it) })
        viewModel.onSuccessDelete.observe(viewLifecycleOwner, Observer { showSuccessDel(it) })
        viewModel.onErrorDelete.observe(viewLifecycleOwner, Observer { showError(it) })
    }

    private fun showSuccessDel(it: ResponseDeleteCartItem?) {
        Toast.makeText(context, it?.message, Toast.LENGTH_SHORT).show()
    }

    private fun showError(it: Throwable?) {
        Toast.makeText(context, it?.message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(it: Boolean?) {
        if (it == true) {
            progressCarts.visibility = View.VISIBLE
        } else {
            progressCarts.visibility = View.GONE
        }
    }

    private fun showCarts(it: ResponseGetCart?) {

        val adapter = CartsAdapter(it?.data as List<DataItemC>?, object : CartsAdapter.OnClickListener{
            override fun detail(item: DataItemC?) {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("id", item?.productId.toString())
                intent.putExtra("cartId", item?.id.toString())
                context?.startActivity(intent)
            }

            override fun delete(item: DataItemC?) {
                confirmDelete(item?.id.toString(), item?.product?.title.toString())
            }

        })
        listCart.adapter = adapter

    }

    private fun confirmDelete(cart_id: String, name: String) {
        AlertDialog.Builder(context).apply {
            setTitle("Hapus")
            setMessage("Yakin hapus $name ?")
            setPositiveButton("Ya") { dialog, which ->
                deleteCartItem(cart_id)
                dialog.dismiss()
                navController.navigate(R.id.cartFragment)

            }
            setNegativeButton("Batal") { dialog, which ->
                dialog.dismiss()
            }
        }.show()
    }

    private fun deleteCartItem(cart_id: String) {
        viewModel.deleteCart(cart_id)
    }
}