package com.irfanarsya.beecommerce.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ConfigNetwork {

    companion object {
        fun provideOkhttpClient(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val builder = OkHttpClient.Builder().addInterceptor(interceptor).apply {
                readTimeout(10, TimeUnit.SECONDS)
                connectTimeout(5, TimeUnit.SECONDS)

            }
            return builder.build()
        }

        fun getRetrofit(): ApiService {
            return Retrofit.Builder().baseUrl(Constant.BASE_URL)
                .client(provideOkhttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }

}