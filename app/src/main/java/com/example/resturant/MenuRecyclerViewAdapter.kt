package com.example.resturant

import android.sax.EndElementListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MenuRecyclerViewAdapter : RecyclerView.Adapter<MenuRecyclerViewAdapter.MenuViewHolder> {
    var menuArrayList = ArrayList<menuItems>()
    var listener :OnClickListener
    constructor(listener: OnClickListener){
        this.listener=listener
    }
    @JvmName("setItems1")
    public fun setMenuArrayList (menuArrayList: ArrayList<menuItems>){
        this.menuArrayList=menuArrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.custom_menu_item,null,false)
        var holder = MenuViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        var menuItems :menuItems=menuArrayList.get(position)
        holder.name.text=menuItems.getName()
        holder.details.text=menuItems.getDetails()
        holder.price.text="$ "+menuItems.getPrice().toString()+".00"
        holder.image.setImageResource(R.drawable.mac_royal)
        holder.itemId= menuItems.getId()!!
    }

    override fun getItemCount(): Int {
        return menuArrayList.size
    }
    inner class MenuViewHolder (itemView :View): RecyclerView.ViewHolder(itemView){
        val view :View =itemView
        val name :TextView
        val details :TextView
        val price :TextView
        val image :ImageView
        var itemId :Int?=null
        init {
            name=itemView.findViewById(R.id.custom_menu_item_tv_name)
            details=itemView.findViewById(R.id.custom_menu_item_tv_details)
            price=itemView.findViewById(R.id.custom_menu_item_tv_price)
            image=itemView.findViewById(R.id.custom_menu_item_iv)
            view.setOnClickListener(object :View.OnClickListener{
                override fun onClick(v: View?) {
                    listener.onClick(itemId!!)
                }

            })
        }


    }
}