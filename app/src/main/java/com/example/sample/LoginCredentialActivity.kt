package com.example.sample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginCredentialActivity : AppCompatActivity() {

    var nextButton: Button? = null
    var textviewusername: TextView? = null
    var textviewpassword: TextView? = null
    var textviewcountry: TextView? = null
    var loginbtn: Button? = null // Changed to Button as it's a button for logout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_credential)

        // Initialize the views
        nextButton = findViewById(R.id.btn_next)
        textviewusername = findViewById(R.id.textview_username)
        textviewpassword = findViewById(R.id.textView_password)
        textviewcountry = findViewById(R.id.textView_country)
        loginbtn = findViewById(R.id.logout_btn)

        // Get intent extras
        val username = intent.getStringExtra("username")
        val password = intent.getStringExtra("password")
        val country = intent.getStringExtra("selectedCountry")

        // Set the text views
        textviewusername?.text = "Username: $username"
        textviewpassword?.text = "Password: $password"
        textviewcountry?.text = "Country: $country"

        // Handle Logout button click
        loginbtn?.setOnClickListener {
            logoutuser()  // Call logoutUser() on logout button click
        }

        // Handle "Next" button click
        nextButton?.setOnClickListener {
            // Navigate to HomeActivity without logging out
            val homeIntent = Intent(this, HomeActivity::class.java)
            startActivity(homeIntent)

        }
    }

    // Logout function
    private fun logoutuser() {
        val sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("USER_ID") // Remove the USER_ID to log out the user
        editor.apply()

        // Navigate to MainActivity (Login screen)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Close the current activity
    }
}
