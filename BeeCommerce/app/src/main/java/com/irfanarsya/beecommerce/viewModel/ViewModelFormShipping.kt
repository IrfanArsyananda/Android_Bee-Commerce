package com.irfanarsya.beecommerce.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irfanarsya.beecommerce.model.action.ResponseEditShipping
import com.irfanarsya.beecommerce.repository.RepositoryUser

class ViewModelFormShipping : ViewModel() {

    val repoEdit = RepositoryUser()

    var onSuccessEditShip = MutableLiveData<ResponseEditShipping>()
    var onErrorEditShip = MutableLiveData<Throwable>()
    var isLoading = MutableLiveData<Boolean>()
    var isKosong = MutableLiveData<Boolean>()

    fun editShip(jdl: String, kt: String, pro: String, add: String,
                 zip: String, isM: String, uId: String, aId: String) {
        isLoading.value = true
        if (jdl.isEmpty() || kt.isEmpty() || pro.isEmpty() || add.isEmpty() ||
                zip.isEmpty() || isM.isEmpty() || uId.isEmpty() || aId.isEmpty()){
            isLoading.value = false
            isKosong.value = true
        }else {
            repoEdit.editShipping(jdl, kt, pro, add, zip, isM, uId, aId,
                    {
                        onSuccessEditShip.value = it
                        isLoading.value = false
                    }, {
                onErrorEditShip.value = it
                isLoading.value = false
            })
        }
    }

    var onSuccessAddShip = MutableLiveData<ResponseEditShipping>()
    var onErrorAddShip = MutableLiveData<Throwable>()

    fun addShip(jdl: String, kt: String, pro: String, add: String,
                 zip: String, isM: String, uId: String) {
        isLoading.value = true
        if (jdl.isEmpty() || kt.isEmpty() || pro.isEmpty() || add.isEmpty() ||
                zip.isEmpty() || isM.isEmpty() || uId.isEmpty()){
            isLoading.value = false
            isKosong.value = true
        }else {
            repoEdit.addShipping(jdl, kt, pro, add, zip, isM, uId,
                    {
                        onSuccessAddShip.value = it
                        isLoading.value = false
                    }, {
                        onErrorAddShip.value = it
                        isLoading.value = false
                    })
        }
    }

}