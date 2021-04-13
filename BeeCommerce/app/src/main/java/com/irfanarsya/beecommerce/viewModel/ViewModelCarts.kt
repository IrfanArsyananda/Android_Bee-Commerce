package com.irfanarsya.beecommerce.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irfanarsya.beecommerce.model.ResponseGetCart
import com.irfanarsya.beecommerce.model.action.ResponseDeleteCartItem
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

}