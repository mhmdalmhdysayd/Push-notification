package com.example.assigmintthreepuchnavigtion

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.assigmintthreepuchnavigtion.API.ApiConfig
import com.example.assigmintthreepuchnavigtion.API.DCallBack
import com.example.assigmintthreepuchnavigtion.API.Model.GlobalModel
import kotlinx.android.synthetic.main.activity_sig_up.*

class SigUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

            super.onCreate(savedInstanceState)
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
            setContentView(R.layout.activity_sig_up)


            btn_go.setOnClickListener {

                val fullName = fullname.text.toString()
                val userName = username.text.toString()
                val Email = email.text.toString()
                val Password = password.text.toString()


                GlobalData().progressDialogKH(this@SigUpActivity,true)
                ApiConfig(this@SigUpActivity,false, object : DCallBack {
                        override fun Result(obj: Any?, funs: String?, IsSuccess: Boolean) {
                            GlobalData().progressDialogKH(this@SigUpActivity, false)
                            if (IsSuccess) {
                                val model: GlobalModel = obj as GlobalModel
                                GlobalData().Toast(this@SigUpActivity, model.msg)
                                val intent = Intent(this@SigUpActivity, LoginActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                val msg = obj as String
                                GlobalData().Toast(this@SigUpActivity, msg)
                            }
                        }
                }).addNewUserApi(fullName,userName,Email,Password)

            }

            backLogin.setOnClickListener {
                finish()
            }

        }
}