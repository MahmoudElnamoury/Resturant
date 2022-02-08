package com.example.resturant

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListRecyclerViewAdapter : RecyclerView.Adapter<ListRecyclerViewAdapter.ListHolder> {
    var items = ArrayList<menuItems>()
    var context :Context?=null
    constructor(items :ArrayList<menuItems>,context: Context){
        this.items=items
        this.context=context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        var view= LayoutInflater.from(parent.context).inflate(R.layout.custom_list_item,null,false)
        var holder= ListHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        var menuItem :menuItems=items.get(position)
        holder.item_iv.setImageResource(R.drawable.mac_royal)
        holder.item_name.text=menuItem.getName()
        holder.item_price.text=menuItem.getPrice().toString()+".00 $"
        holder.view.setOnClickListener{
            var intent =Intent(context,Details::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("list_intent","extra3")
            intent.putExtra("list item",menuItem)
            context!!.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
    class ListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var view =itemView
        val item_iv:ImageView
        val item_name:TextView
        val item_price:TextView
        init {
            item_iv=itemView.findViewById(R.id.custom_list_item_iv)
            item_name=itemView.findViewById(R.id.custom_list_item_tv_name)
            item_price=itemView.findViewById(R.id.custom_list_item_tv_price)
        }
    }
}