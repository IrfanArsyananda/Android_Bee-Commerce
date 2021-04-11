package com.irfanarsya.beecommerce.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irfanarsya.beecommerce.model.ResponseGetProfile
import com.irfanarsya.beecommerce.model.ShippingAddressItem
import com.irfanarsya.beecommerce.repository.RepositoryUser

class ViewModelShipping: ViewModel() {

    val repoShipping = RepositoryUser()

    var onSuccessGetShip = MutableLiveData<ResponseGetProfile>()
    var onErrorGetShip = MutableLiveData<Throwable>()
    var isLoading = MutableLiveData<Boolean>()

    fun getProfil(id: String) {
        isLoading.value = true

        repoShipping.getProfil(id,
            {
                onSuccessGetShip.value = it
                isLoading.value = false
            }, {
                onErrorGetShip.value = it
                isLoading.value = false
            })

    }

}