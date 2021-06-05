package com.example.assigmintthreepuchnavigtion

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.assigmintthreepuchnavigtion.API.ApiConfig
import com.example.assigmintthreepuchnavigtion.API.DCallBack
import com.example.assigmintthreepuchnavigtion.API.Model.GlobalModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

        var token = ""
        override fun onCreate(savedInstanceState: Bundle?) {

            super.onCreate(savedInstanceState)
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
            setContentView(R.layout.activity_login)

            getToken()

            GoSignin.setOnClickListener {
                startActivity(Intent(applicationContext, SigUpActivity::class.java))
            }

            login_btn.setOnClickListener {
                val username = username.text.toString()
                val password = password.text.toString()

                if (username.isEmpty()) {
                    GlobalData().Toast(this@LoginActivity,"Enter you Email")
                    return@setOnClickListener
                }
                if (password.isEmpty()) {
                    GlobalData().Toast(this@LoginActivity,"Enter you Password")
                    return@setOnClickListener
                }

                GlobalData().progressDialogKH(this@LoginActivity, true)
                ApiConfig(this,false, object : DCallBack {
                    override fun Result(obj: Any?, funs: String?, IsSuccess: Boolean) {
                        if (IsSuccess) {
                            val model = obj as GlobalModel
                            GlobalData().Toast(this@LoginActivity, model.msg)
                            ApiConfig(this@LoginActivity, false, object : DCallBack {
                                override fun Result(obj: Any?, funs: String?, IsSuccess: Boolean) {
                                        GlobalData().progressDialogKH(this@LoginActivity, false)
                                        if (IsSuccess) {
                                            val model = obj as GlobalModel
                                            GlobalData().Toast(this@LoginActivity, model.msg)
                                            startActivity(
                                                Intent(
                                                    this@LoginActivity,
                                                    MainActivity::class.java
                                                )
                                            )
                                        } else {
                                            val msg = obj as String
                                            GlobalData().Toast(this@LoginActivity, msg)
                                        }
                                    }
                            }).addRegTokenApi(username, password, token)
                        } else {
                            GlobalData().progressDialogKH(this@LoginActivity, false)
                            val msg = obj as String
                            GlobalData().Toast(this@LoginActivity, msg)
                        }
                    }
                }).loginApi(username,password)
            }


        }

        private fun getToken() {
            FirebaseMessaging.getInstance().token
                .addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.e("tokenF", "Failed Token")
                        return@OnCompleteListener
                    }
                    token = task.result.toString()
                    Log.e("token", "Token : $token")
                })
        }
    }