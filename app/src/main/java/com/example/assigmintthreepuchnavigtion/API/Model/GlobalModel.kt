package com.example.assigmintthreepuchnavigtion.API.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

public class GlobalModel {

    @SerializedName("status")
    @Expose
    var status: Boolean? = null

    @SerializedName("statusCode")
    @Expose
    var statusCode: Int? = null

    @SerializedName("msg")
    @Expose
    var msg: String? = null

}