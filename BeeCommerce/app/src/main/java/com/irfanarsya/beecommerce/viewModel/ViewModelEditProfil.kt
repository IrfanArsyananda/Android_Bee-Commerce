package com.irfanarsya.beecommerce.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irfanarsya.beecommerce.model.action.ResponseEditProfil
import com.irfanarsya.beecommerce.repository.RepositoryUser

class ViewModelEditProfil : ViewModel() {

    val repoEditProfil = RepositoryUser()

    var onSuccessEditProfil = MutableLiveData<ResponseEditProfil>()
    var onErrorEditProfil = MutableLiveData<Throwable>()
    var isLoading = MutableLiveData<Boolean>()

    fun editProfil(fName: String, lName: String, uId: String) {
        isLoading.value = true

        repoEditProfil.editProfil(fName, lName, uId,
            {
                onSuccessEditProfil.value = it
                isLoading.value = false
            }, {
                onErrorEditProfil.value = it
                isLoading.value = false
            })

    }

}