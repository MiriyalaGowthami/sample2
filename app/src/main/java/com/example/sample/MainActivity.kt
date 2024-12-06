package com.example.sample

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    var username:EditText?= null
    var countryname:Spinner?=null
    var bun:Button?=null
    var checkbox:CheckBox?=null
    var password:EditText?=null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        username=findViewById(R.id.ed_username)
        countryname=findViewById(R.id.spinner_country)
        bun=findViewById(R.id.btn)
        checkbox=findViewById(R.id.checkbox_terms)
        password=findViewById(R.id.ed_password)

        var countries=resources.getStringArray(R.array.countries_array)
        var adapter= ArrayAdapter(this,android.R.layout.simple_spinner_item,countries)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        countryname?.adapter=adapter

        bun?.setOnClickListener {
            var usernameText = username?.text.toString()
            var isChecked = checkbox?.isChecked == true
            var passwordText=password?.text.toString()
            var passwordPattern = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#\\$\\%\\^&*])[a-zA-Z0-9!@#\\$\\%\\^&*]+$");
            var selectedCountry = countryname?.selectedItem.toString()


            var error_msg= when{
                usernameText.isEmpty() &&  passwordText.isEmpty()-> "Please fill in all details."
                usernameText.isEmpty() -> "Please enter your username."
                selectedCountry.isEmpty() -> "Please select your country."
                !isChecked -> "Please agree to the Terms and Conditions."
                passwordText.isEmpty()->"Please enter your password."
                !passwordPattern.matcher(passwordText).matches() -> "Password must contain at least one letter, one number, and one special character, and be at least 6 characters long."
                else->null
            }

            if(error_msg!=null ){
                //Toast.makeText( this,error_msg,Toast.LENGTH_SHORT).show()
                showAlert(error_msg)
            }

            else{
                Toast.makeText(this,"Registration successful", Toast.LENGTH_SHORT).show()
                //showAlert()
                Log.d("submit values",""+ usernameText + " "+ " "+ passwordText+" "+ selectedCountry)
                var loginIntent=Intent(this, LoginCredentialActivity::class.java)
                loginIntent.putExtra("username",usernameText)
                loginIntent.putExtra("password",passwordText)
                loginIntent.putExtra("selectedCountry",selectedCountry)

                // saving the user details into the shared preferences
                saveUserDetails(usernameText,passwordText,selectedCountry,this)
                startActivity(loginIntent)
                finish()

            }
       }
    }

    private fun showAlert(errormessage:String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("lOGIN ERROR")
        builder.setMessage(errormessage)

        // Set a positive button with action
        builder.setPositiveButton("OK") { dialog, which ->
            // Perform any action on click of OK button
            dialog.dismiss()
        }

        // Set a negative button if needed
        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.dismiss()
        }

        // Create and show the alert dialog
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    // Function to save User ID in SharedPreferences
    fun saveUserDetails(userName: String,password:String,country:String, context: Context) {
        val sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("USER_ID", userName)
        editor.putString("PASSWORD", password)
        editor.putString("COUNTRY", country)
        editor.apply()
    }

}