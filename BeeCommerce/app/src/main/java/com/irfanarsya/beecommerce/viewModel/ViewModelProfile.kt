package com.irfanarsya.beecommerce.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irfanarsya.beecommerce.model.ResponseGetProfile
import com.irfanarsya.beecommerce.repository.RepositoryUser

class ViewModelProfile: ViewModel() {

    val repoProfil = RepositoryUser()

    var onSuccessGetProfil = MutableLiveData<ResponseGetProfile>()
    var onErrorGetProfil = MutableLiveData<Throwable>()
    var isLoading = MutableLiveData<Boolean>()

    fun getProfil(id: String) {
        isLoading.value = true

        repoProfil.getProfil(id,
            {
                onSuccessGetProfil.value = it
                isLoading.value = false
            }, {
                onErrorGetProfil.value = it
                isLoading.value = false
            })

    }

}