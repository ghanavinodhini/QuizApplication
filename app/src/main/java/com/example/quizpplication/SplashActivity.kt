package com.example.quizpplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    val SPLASH_TIME_OUT = 2000L
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        animateSplashImage()
        //Display splash Activity for 20 sec and launch Main Activity
        handler = Handler()
        handler.postDelayed({
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        },SPLASH_TIME_OUT)
    }

    //Implement rotate animation for splash image
    private fun animateSplashImage()
    {
        val imageAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate)
        splashImageView.startAnimation(imageAnimation)
    }
}