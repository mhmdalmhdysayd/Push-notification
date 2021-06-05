package com.example.assigmintthreepuchnavigtion.API

import android.app.Activity
import android.text.TextUtils
import android.widget.Toast
import com.example.assigmintthreepuchnavigtion.API.Model.GlobalModel
import com.example.assigmintthreepuchnavigtion.R
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import java.io.IOException
import java.util.*


public class ApiConfig{

     var callBack: DCallBack? = null
     var apiService: ApiInterface? = null
     var activity: Activity? = null
     var subscribeOn: Scheduler? = null
     var observeOn: Scheduler? = null


    constructor(
        activity: Activity?,
        authorization: Boolean,
        callBack: DCallBack?
    ) {
        this.activity = activity
        this.callBack = callBack
        apiService = ApiClient().getClient(authorization)!!.create(ApiInterface::class.java)
        subscribeOn = Schedulers.io()
        observeOn = AndroidSchedulers.mainThread()
    }

    fun loginApi(email: String, password: String) {
        val params: MutableMap<Any, Any> =
            HashMap()
        params["email"] = email
        params["password"] = password
        apiService!!.login(params)
            ?.subscribeOn(subscribeOn)
            ?.observeOn(observeOn)
            ?.subscribe(object : DisposableSingleObserver<GlobalModel?>() {
                override fun onSuccess(model: GlobalModel) {
                    if (model.status!!) {
                        callBack!!.Result(model, "userLoginApi", true)
                    } else {
                        callBack!!.Result(model.msg, "userLoginApi", false)
                    }
                }

                override fun onError(e: Throwable) {
                    callBack!!.Result(showError(e), "userLoginApi", false)
                }
            })
    }

    fun addNewUserApi(
        firstName: String,
        secondName: String,
        email: String,
        password: String
    ) {
        val params: MutableMap<Any, Any> =
            HashMap()
        params["firstName"] = firstName
        params["secondName"] = secondName
        params["email"] = email
        params["password"] = password
        apiService!!.addNewUser(params)
            ?.subscribeOn(subscribeOn)
            ?.observeOn(observeOn)
            ?.subscribe(object : DisposableSingleObserver<GlobalModel?>() {
                override fun onSuccess(model: GlobalModel) {
                    if (model.status!!) {
                        callBack!!.Result(model, "addNewUserApi", true)
                    } else {
                        callBack!!.Result(model.msg, "addNewUserApi", false)
                    }
                }

                override fun onError(e: Throwable) {
                    callBack!!.Result(showError(e), "addNewUserApi", false)
                }
            })
    }

    fun addRegTokenApi(
        email: String,
        password: String,
        reg_token: String
    ) {
        val params: MutableMap<Any, Any> =
            HashMap()
        params["email"] = email
        params["password"] = password
        params["reg_token"] = reg_token
        apiService!!.addRegToken(params)
            ?.subscribeOn(subscribeOn)
            ?.observeOn(observeOn)
            ?.subscribe(object : DisposableSingleObserver<GlobalModel?>() {
                override fun onSuccess(model: GlobalModel) {
                    if (model.status!!) {
                        callBack!!.Result(model, "addRegTokenApi", true)
                    } else {
                        callBack!!.Result(model.msg, "addRegTokenApi", false)
                    }
                }

                override fun onError(e: Throwable) {
                    callBack!!.Result(showError(e), "addRegTokenApi", false)
                }
            })
    }

    private fun showError(e: Throwable): String? {
        var message: String? = ""
        try {
            if (e is IOException) {
                message = activity!!.getString(R.string.no_internet)
            } else if (e is HttpException) {
                val error =
                    e
                if (error.response().code() == 401) {
                    val errorBody = error.response().errorBody()!!.string()
                    val jObj = JSONObject(errorBody)
                    message = jObj.getString("msg")
                    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
                } else {
                    val errorBody = error.response().errorBody()!!.string()
                    val jObj = JSONObject(errorBody)
                    message = jObj.getString("msg")
                }
            } else {
                message = e.message
            }
        } catch (e1: Exception) {
            e1.printStackTrace()
        }
        if (TextUtils.isEmpty(message)) {
            message = "Unknown error occurred! Check LogCat."
        }
        return message
    }

}