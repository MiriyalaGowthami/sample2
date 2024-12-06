package com.example.sample

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RecipeDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipedetails_activity)

        // Retrieve data from the intent
        val ingredients = intent.getStringExtra("ingredients")
        val steps = intent.getStringExtra("process")

        // Display the data
        findViewById<TextView>(R.id.text_ingredients).text = ingredients
        findViewById<TextView>(R.id.text_steps).text = steps
    }
}
