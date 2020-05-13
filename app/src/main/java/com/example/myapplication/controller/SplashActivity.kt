package com.example.myapplication.controller

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.MainActivity


class SplashActivity : AppCompatActivity() {

    private lateinit var img: ImageView
    private lateinit var txt: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        img = findViewById(R.id.splash_logo)
        txt = findViewById(R.id.bitHubSplashText)


        val animFadeIn: Animation =
            AnimationUtils.loadAnimation(applicationContext, R.anim.fade_in)
        val animFadeInNext: Animation =
            AnimationUtils.loadAnimation(applicationContext, R.anim.fade_in_second)
        img.startAnimation(animFadeIn)
        txt.startAnimation(animFadeInNext)


        val SPLASH_TIME_OUT = 3000 //2 seconds until the entire splash activity goes to mainactivity
        val homeIntent = Intent(this@SplashActivity, MainActivity::class.java)
        Handler().postDelayed({
            startActivity(homeIntent)
            finish()
        }, SPLASH_TIME_OUT.toLong())


    }
}
