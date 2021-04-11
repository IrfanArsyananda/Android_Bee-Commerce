package com.irfanarsya.beecommerce.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irfanarsya.beecommerce.model.action.ResponseEditFoto
import com.irfanarsya.beecommerce.repository.RepositoryUser
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class ViewModelEditFoto : ViewModel() {

    val repoUpdateFoto = RepositoryUser()

    var onSuccess = MutableLiveData<ResponseEditFoto>()
    var onError = MutableLiveData<Throwable>()

    var isKosong = MutableLiveData<Boolean>()
    var isLoading = MutableLiveData<Boolean>()

    fun updateFoto(user_id: String, path: String) {
        isLoading.value = true

        if (path.isEmpty() || path.equals(null)) {
            isKosong.value = true
            isLoading.value = false
        } else {
            val idUser: RequestBody = user_id.toRequestBody("multipart/form-data".toMediaTypeOrNull())

            val file = File(path)
            val requestFile: RequestBody = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
            val body: MultipartBody.Part = MultipartBody.Part.createFormData("file", file.name, requestFile)

            repoUpdateFoto.editFoto(idUser, body,
                {
                    onSuccess.value = it
                    isLoading.value = false
                },
                {
                    onError.value = it
                    isLoading.value = false
                }
            )
        }


    }
}