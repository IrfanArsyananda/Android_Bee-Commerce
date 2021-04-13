package com.irfanarsya.beecommerce.network

import com.irfanarsya.beecommerce.model.*
import com.irfanarsya.beecommerce.model.action.*
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiService {

    //register
    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("email") email: String,
        @Field("first_name") first_name: String,
        @Field("last_name") last_name: String,
        @Field("password") password: String,
    ): Single<ResponseRegister>

    //login
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

    //getHomeProductsByKey
    @GET("get-home-products")
    fun getHomeProductsByKey(
        @Query("page") page : Long,
        @Query("pageSize") pageSize : Int,
        @Query("q") key : String
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

    //getCarts
    @GET("get-shopping-carts")
    fun getCart(
        @Query("user_id") user_id : Int
    ):Flowable<ResponseGetCart>

    //addToCart
    @FormUrlEncoded
    @POST("insert-cart-item")
    fun addToCart(
        @Field("product_id") product_id: Int,
        @Field("user_id") user_id: Int,
        @Field("qty") qty: Int,
    ): Single<ResponseAddToCart>

    //updateCart
    @FormUrlEncoded
    @POST("update-cart-item-qty")
    fun updateCart(
        @Field("user_id") user_id: Int,
        @Field("new_qty") new_qty: Int,
        @Field("cart_id") cart_id: Int,
    ): Single<ResponseEditCart>

    //deleteItemCart
    @FormUrlEncoded
    @POST("delete-cart-item")
    fun deleteCart(
        @Field("cart_id") cart_id: Int,
    ): Single<ResponseDeleteCartItem>

    //getProfile
    @GET("get-profile")
    fun getProfile(
        @Query("user_id") user_id : String
    ):Flowable<ResponseGetProfile>

    //updateProfil
    @FormUrlEncoded
    @POST("update-profile")
    fun updateProfil(
        @Field("first_name") first_name: String,
        @Field("last_name") last_name: String,
        @Field("user_id") user_id: String,
    ): Single<ResponseEditProfil>

    //updateFotoProfil
    @Multipart
    @POST("update-photo-profile")
    fun updateFoto(
        @Part("user_id") user_id: RequestBody,
        @Part file: MultipartBody.Part
    ): Single<ResponseEditFoto>

    //addOrder
    @FormUrlEncoded
    @POST("insert-order")
    fun addOrder(
        @Field("user_id") user_id: Int,
    ): Single<ResponseAddOrder>

    //addShipping
    @FormUrlEncoded
    @POST("update-shipping-address")
    fun addShipping(
            @Field("title") title: String,
            @Field("city") city: String,
            @Field("province") province: String,
            @Field("address") address: String,
            @Field("zip_code") zip_code: String,
            @Field("is_main_address") is_main_address: String,
            @Field("user_id") user_id: String,
    ): Single<ResponseEditShipping>

    //updateShipping
    @FormUrlEncoded
    @POST("update-shipping-address")
    fun updateShipping(
            @Field("title") title: String,
            @Field("city") city: String,
            @Field("province") province: String,
            @Field("address") address: String,
            @Field("zip_code") zip_code: String,
            @Field("is_main_address") is_main_address: String,
            @Field("user_id") user_id: String,
            @Field("address_id") address_id: String,
    ): Single<ResponseEditShipping>

}