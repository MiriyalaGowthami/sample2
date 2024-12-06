package com.example.sample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(
    val itemlist: List<String>,
    val pricelist: List<String>,
    val recipelist:List<RecipeProcess>,
    val callback: ItemClickCallback): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val textview:TextView=view.findViewById(R.id.text_items)
        val priceview:TextView=view.findViewById(R.id.text_prices)
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
       val item= itemlist[position]
        val price=pricelist[position]
        val recipe=recipelist[position]
        holder.textview.text=item
        holder.priceview.text=price

        holder.itemView.setOnClickListener {
            callback.onItemClick(item, recipe)

    }
                   }

    override fun getItemCount():Int {
        return minOf(itemlist.size, pricelist.size,recipelist.size)

    }
    interface ItemClickCallback{
        fun onItemClick(item: String, recipe: RecipeProcess)
    }

}