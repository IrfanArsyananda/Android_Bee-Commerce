package com.irfanarsya.beecommerce.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irfanarsya.beecommerce.model.ResponseGetCart
import com.irfanarsya.beecommerce.model.action.ResponseAddOrder
import com.irfanarsya.beecommerce.model.action.ResponseAddToCart
import com.irfanarsya.beecommerce.model.action.ResponseDeleteCartItem
import com.irfanarsya.beecommerce.model.action.ResponseEditCart
import com.irfanarsya.beecommerce.repository.RepositoryProducts

class ViewModelCarts : ViewModel() {

    val repoCarts = RepositoryProducts()

    var onSuccessGetCarts = MutableLiveData<ResponseGetCart>()
    var onErrorGetCarts = MutableLiveData<Throwable>()
    var isLoading = MutableLiveData<Boolean>()

    fun getCarts(user_id: String) {
        val idC = user_id.toInt()
        isLoading.value = true

        repoCarts.getCarts(idC,
            {
                onSuccessGetCarts.value = it
                isLoading.value = false
            }, {
                onErrorGetCarts.value = it
                isLoading.value = false
            })

    }

    var onSuccessUpdate = MutableLiveData<ResponseEditCart>()
    var onErrorUpdate = MutableLiveData<Throwable>()

    fun updateCart(userId: String, newQty:String, cartId: String) {
        val uIdC = userId.toInt()
        val nQC = newQty.toInt()
        val cIdC = cartId.toInt()
        isLoading.value = true

        repoCarts.updateCarts(uIdC, nQC, cIdC,
            {
                onSuccessUpdate.value = it
                isLoading.value = false
            }, {
                onErrorUpdate.value = it
                isLoading.value = false
            })

    }

    var onSuccessDelete = MutableLiveData<ResponseDeleteCartItem>()
    var onErrorDelete = MutableLiveData<Throwable>()

    fun deleteCart(cart_id: String) {
        val idC = cart_id.toInt()
        isLoading.value = true

        repoCarts.deleteCart(idC,
            {
                onSuccessDelete.value = it
                isLoading.value = false
            }, {
                onErrorDelete.value = it
                isLoading.value = false
            })

    }

    var onSuccessAddOrder = MutableLiveData<ResponseAddOrder>()
    var onErrorAddOrder = MutableLiveData<Throwable>()

    fun addOrder(userId: String) {
        val idU = userId.toInt()
        isLoading.value = true

        repoCarts.addOrder(idU,
            {
                onSuccessAddOrder.value = it
                isLoading.value = false
            }, {
                onErrorAddOrder.value = it
                isLoading.value = false
            })

    }

}