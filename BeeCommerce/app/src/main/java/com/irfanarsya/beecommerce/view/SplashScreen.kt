package com.irfanarsya.beecommerce.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.irfanarsya.beecommerce.view.home.MainActivity
import com.irfanarsya.beecommerce.R
import com.irfanarsya.beecommerce.helper.SessionManager

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val session = SessionManager(this)

        Handler().postDelayed(Runnable {
            if (session.login ?: true){
                startActivity(Intent(this, MainActivity::class.java))
            }else{
                startActivity(Intent(this, LoginActivity::class.java))
            }
            finish()
        }, 1000)

    }
}