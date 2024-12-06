package com.example.sample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashActivity : AppCompatActivity() {
    private val splashTimeOut: Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed({
            // Check if User ID is saved in SharedPreferences
            val sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
            val userId = sharedPreferences.getString("USER_ID", null)
            val password = sharedPreferences.getString("PASSWORD", null)
            val country = sharedPreferences.getString("COUNTRY", null)


            if (userId != null) {
                // If user is logged in, navigate to MainActivity
                val intent = Intent(this,LoginCredentialActivity::class.java)
                intent.putExtra("username",userId)
                intent.putExtra("password",password)
                intent.putExtra("selectedCountry",country)
                startActivity(intent)
            } else {
                // If user is not logged in, navigate to LoginActivity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

            finish() // Close SplashActivity
        }, splashTimeOut)

    }
}