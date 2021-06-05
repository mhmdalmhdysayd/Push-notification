package com.example.assigmintthreepuchnavigtion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splach.*

class SplachActivity : AppCompatActivity() {

            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
                setContentView(R.layout.activity_splach)

        //Animations
                val topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
                val bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

                //Set animation to elements
                imageView.setAnimation(topAnim)
                textView.setAnimation(bottomAnim)
                textView2.setAnimation(bottomAnim)

                var background = object : Thread() {
                    override fun run() {
                        try {
                            Thread.sleep(4000)
                            val i = Intent(applicationContext, LoginActivity::class.java)
                            startActivity(i)

                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }

                }
                background.start()


            }
}