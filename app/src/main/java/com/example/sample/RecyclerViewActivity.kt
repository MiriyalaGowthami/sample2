package com.example.sample

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewActivity : AppCompatActivity() {

    private var foodlist: RecyclerView? = null
    private var recyclerviewadapter: RecyclerViewAdapter? = null
    val recipes= mutableListOf<RecipeProcess>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        // Initialize RecyclerView
        foodlist = findViewById(R.id.recyclerview)
        foodlist?.layoutManager = LinearLayoutManager(this)

        // Get the list of food items from resources
        val fooditems = resources.getStringArray(R.array.food_items_array).toList()
        val foodprices = resources.getStringArray(R.array.food_prices_array).toList()
        loadrecipeprocess()

        // Set up the adapter with an anonymous implementation of the ItemClickCallback
        recyclerviewadapter = RecyclerViewAdapter(
            fooditems,
            foodprices,
            recipes,
            object : RecyclerViewAdapter.ItemClickCallback {
                override fun onItemClick(item: String, recipe:RecipeProcess) {

                    // Show a Toast message when an item is clicked
                    Toast.makeText(
                        this@RecyclerViewActivity,
                        "$item is Clicked",
                        Toast.LENGTH_SHORT
                    ).show()

                // Create an Intent to start RecipeDetailsActivity
                val intent = Intent(this@RecyclerViewActivity,RecipeDetailsActivity ::class.java)

                // Put extra data into the intent
                intent.putExtra("item_name", item)
                intent.putExtra("ingredients", recipe.ingredients)
                intent.putExtra("process", recipe.steps)

                // Start the RecipeDetailsActivity
                startActivity(intent)
            }
            })

        // Set the adapter to the RecyclerView
        foodlist?.adapter = recyclerviewadapter
    }
    private fun loadrecipeprocess(){
        recipes.add(RecipeProcess("milk,butter","add milk and butter"))
        recipes.add(RecipeProcess("milk,butter","add milk and butter"))
        recipes.add(RecipeProcess("milk,butter","add milk and butter"))
        recipes.add(RecipeProcess("milk,butter","add milk and butter"))
        recipes.add(RecipeProcess("milk,butter","add milk and butter"))

    }

}