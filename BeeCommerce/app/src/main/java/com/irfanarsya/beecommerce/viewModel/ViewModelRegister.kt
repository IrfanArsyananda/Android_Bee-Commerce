package com.irfanarsya.beecommerce.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irfanarsya.beecommerce.model.ResponseRegister
import com.irfanarsya.beecommerce.repository.RepositoryUser

class ViewModelRegister: ViewModel() {

    val repoRegister = RepositoryUser()

    var onSuccessRegister = MutableLiveData<ResponseRegister>()
    var onErrorRegister = MutableLiveData<String>()
    var isLoading = MutableLiveData<Boolean>()

    fun register(first_name: String, last_name: String, email: String, password: String, confirmPassword: String){

        isLoading.value = true

        if (first_name.isEmpty() || last_name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
            onErrorRegister.value = "KOSONG"
            isLoading.value = false
        }else {
            if (password != confirmPassword){
                onErrorRegister.value = "NOMATCH"
                isLoading.value = false
            }else if (password.length < 6){
                onErrorRegister.value = "LESS6"
                isLoading.value = false
            }else{
                repoRegister.register(email, first_name, last_name, password,
                    {
                        if (it.isSuccess == true){
                            onSuccessRegister.value = it
                            isLoading.value = false
                        }else{
                            onErrorRegister.value = it.message.toString()
                            isLoading.value = false
                        }
                    },  {
                        onErrorRegister.value = it.localizedMessage
                        isLoading.value = false
                    })
            }
        }
    }

}