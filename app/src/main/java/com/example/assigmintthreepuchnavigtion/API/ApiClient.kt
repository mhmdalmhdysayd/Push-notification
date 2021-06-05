package com.example.assigmintthreepuchnavigtion.API

import androidx.annotation.NonNull
import org.jetbrains.annotations.NotNull
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import okhttp3.Interceptor
import okhttp3.OkHttpClient

import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiClient {

    private val TAG = "ApiClient"


    fun getClient(authorization: Boolean): Retrofit? {
        val logging = HttpLoggingInterceptor()
        //        if (BuildConfig.DEBUG) {
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        //        } else {
//            logging.setLevel(HttpLoggingInterceptor.Level.NONE);
//        }
        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
        httpClient.addInterceptor(logging)
        if (authorization) {
        //System.out.println("get AccessToken "+UserAuth.getInstance().getUser().getAccessToken());
            httpClient.addInterceptor(object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    val original: Request = chain.request()
                    val request: Request = original.newBuilder()
                        .header("Content-Type", "application/x-www-form-urlencoded")
                        .header("Accept", "application/json")
                        .header(
                            "type",
                            "android"
                        )
                        //.header("Accept-Language", UserAuth.getInstance().getLang() != null ? UserAuth.getInstance().getLang() : "ar")
                        //.header("Authorization", "Bearer "+UserAuth.getInstance().getUser().getAccessToken())
                        //.header("Authorization", "Bearer 53|KvhQ0YyBCGOGQ4qT791rd8ZJx46t6yHjnJYS0pum")
                        .method(original.method, original.body)
                        .build()
                    return chain.proceed(request)
                }
            }
            )
        } else {
            httpClient.addInterceptor(object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    val original: Request = chain.request()
                    val request: Request = original.newBuilder()
                        .header("Content-Type", "application/x-www-form-urlencoded")
                        .header("Accept", "application/json")
                        .header(
                            "type",
                            "android"
                        )
                      //.header("Accept-Language", UserAuth.getInstance().getLang() != null ? UserAuth.getInstance().getLang() : "ar")
                        .method(original.method, original.body)
                        .build()
                    return chain.proceed(request)
                }
            }
            )
        }
        return Retrofit.Builder()
            .baseUrl("https://mcc-users-api.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build())
            .build()
    }


}