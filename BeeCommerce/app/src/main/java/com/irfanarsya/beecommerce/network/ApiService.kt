package com.irfanarsya.beecommerce.network

import com.irfanarsya.beecommerce.model.*
import com.irfanarsya.beecommerce.model.action.ResponseEditFoto
import com.irfanarsya.beecommerce.model.action.ResponseEditProfil
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("email") email: String,
        @Field("first_name") first_name: String,
        @Field("last_name") last_name: String,
        @Field("password") password: String,
    ): Single<ResponseRegister>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Flowable<ResponseLogin>

    //getHomeProducts
    @GET("get-home-products")
    fun getHomeProducts(
        @Query("page") page : Long,
        @Query("pageSize") pageSize : Int
    ):Flowable<ResponseGetHome>

    //getPromo
    @GET("get-promotion-products")
    fun getPromo(
        @Query("page") page : Long,
        @Query("pageSize") pageSize : Int
    ):Flowable<ResponseGetHome>

    //getDetailProduct
    @GET("get-product")
    fun getDetailProduct(
            @Query("product_id") product_id : Int
    ):Flowable<ResponseDetailProduct>

    //getCategory
    @GET("get-categories")
    fun getCategories(
            @Query("page") page : Long,
            @Query("pageSize") pageSize : Int
    ):Flowable<List<CategoryItem>>

    //getProfile
    @GET("get-profile")
    fun getProfile(
        @Query("user_id") user_id : String
    ):Flowable<ResponseGetProfile>

//    //getShipping
//    @GET("get-profile")
//    fun getShipping(
//        @Query("user_id") user_id : String
//    ):Flowable<List<ShippingAddressItem>>

    @FormUrlEncoded
    @POST("update-profile")
    fun updateProfil(
        @Field("first_name") first_name: String,
        @Field("last_name") last_name: String,
        @Field("user_id") user_id: String,
    ): Flowable<ResponseEditProfil>

    //updateFoto
    @Multipart
    @POST("update-photo-profile")
    fun updateFoto(
        @Part("user_id") user_id: RequestBody,
        @Part file: MultipartBody.Part
    ): Single<ResponseEditFoto>

}