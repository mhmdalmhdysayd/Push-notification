package com.example.assigmintthreepuchnavigtion.API

import com.example.assigmintthreepuchnavigtion.API.Model.GlobalModel
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

public interface ApiInterface {

    @POST("login")
    fun login(@Body params: MutableMap<Any, Any>): Single<GlobalModel?>?

    @POST("add_new_user")
    fun addNewUser(@Body params: MutableMap<Any, Any>): Single<GlobalModel?>?

    @PUT("add_reg_token")
    fun addRegToken(@Body params: MutableMap<Any, Any>): Single<GlobalModel?>?

}