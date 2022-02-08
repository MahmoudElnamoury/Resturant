package com.example.resturant

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class SearchRecyclerViewAdapter : RecyclerView.Adapter<SearchRecyclerViewAdapter.SearchViewHolder> {
    var items = ArrayList<menuItems>()
    var context:Context?=null
    var listener :OnClickListener
    var position:Int = 0

    constructor(listener: OnClickListener ,context: Context){
        this.listener =listener
        this.context=context
    }
    @JvmName("setItems1")
    public fun setItems(items: ArrayList<menuItems>){
        this.items=items
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        var view =LayoutInflater.from(parent.context).inflate(R.layout.custom_menu_item,null,false)
        var holder=SearchViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        this.position =position
        var menuItem :menuItems=items.get(position)
        holder.name.text=menuItem.getName()
        holder.details.text=menuItem.getDetails()
        holder.price.text="$ "+menuItem.getPrice().toString()+".00"
        holder.image.setImageResource(R.drawable.mac_royal)
        holder.itemId=menuItem.getId()

        //listener of itemView
        holder.view.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {

//
                //send id to the search ( activity ) to get the item from the database
                listener.onClick(holder.itemId!!)
            }

        })
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class SearchViewHolder(itemview :View) : RecyclerView.ViewHolder(itemview){
        var view :View=itemView
        val name : TextView
        val details : TextView
        val price : TextView
        val image : ImageView
        var itemId :Int?=null
        init {
            name=itemView.findViewById(R.id.custom_menu_item_tv_name)
            details=itemView.findViewById(R.id.custom_menu_item_tv_details)
            price=itemView.findViewById(R.id.custom_menu_item_tv_price)
            image=itemView.findViewById(R.id.custom_menu_item_iv)
//            view.setOnClickListener(object :View.OnClickListener{
//                override fun onClick(v: View?) {
//                    listener.onClick(itemId!!)
//                }
//            })
        }

    }

}