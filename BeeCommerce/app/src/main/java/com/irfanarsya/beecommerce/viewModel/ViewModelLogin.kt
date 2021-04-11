package com.irfanarsya.beecommerce.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irfanarsya.beecommerce.model.ResponseLogin
import com.irfanarsya.beecommerce.repository.RepositoryUser

class ViewModelLogin : ViewModel() {

    val repoLogin = RepositoryUser()

    var onSuccessLogin = MutableLiveData<ResponseLogin>()
    var onErrorLogin = MutableLiveData<Throwable>()
    var isLoading = MutableLiveData<Boolean>()

    fun getLogin(email: String, password: String) {
        isLoading.value = true

        repoLogin.login(email, password,
            {
                onSuccessLogin.value = it
                isLoading.value = false
            }, {
                onErrorLogin.value = it
                isLoading.value = false
            })

    }

}