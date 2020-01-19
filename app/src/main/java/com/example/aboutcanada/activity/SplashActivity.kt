package com.example.aboutcanada.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.aboutcanada.R

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIMEOUT = 3000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(Runnable {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }, SPLASH_TIMEOUT)
    }
}
