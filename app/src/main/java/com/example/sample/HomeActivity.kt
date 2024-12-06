package com.example.sample

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class HomeActivity: AppCompatActivity() {
    var openfoodlist:Button?=null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_home) // Ensure this layout file exists in res/layout

            openfoodlist=findViewById(R.id.foods)

            openfoodlist?.setOnClickListener  {
                val recyclerviewintent=Intent(this,RecyclerViewActivity::class.java)
                startActivity(recyclerviewintent)
            }

        }

    }



