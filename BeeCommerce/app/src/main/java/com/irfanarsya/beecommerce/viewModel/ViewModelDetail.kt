package com.irfanarsya.beecommerce.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irfanarsya.beecommerce.model.ResponseDetailProduct
import com.irfanarsya.beecommerce.repository.RepositoryProducts

class ViewModelDetail : ViewModel() {

    val repoDetail = RepositoryProducts()

    var onSuccess = MutableLiveData<ResponseDetailProduct>()
    var onError = MutableLiveData<Throwable>()
    var isLoading = MutableLiveData<Boolean>()

    fun getDetail(id: String) {
        val idC = id.toInt()
        isLoading.value = true

        repoDetail.getDetailProduct(idC,
            {
                onSuccess.value = it
                isLoading.value = false
            }, {
                onError.value = it
                isLoading.value = false
            })

    }

}