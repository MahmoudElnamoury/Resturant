package com.example.resturant

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartRecyclerViewAdapter : RecyclerView.Adapter<CartRecyclerViewAdapter.CartViewHolder>{
    var cartArrayList=ArrayList<menuItems>()
    var context:Context? =null
    constructor(cartArrayList: ArrayList<menuItems>,context: Context){
        this.cartArrayList=cartArrayList
        this.context=context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.custom_cart_item,null,false)
        var holder=CartViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        var menuItem=cartArrayList.get(position)
        holder.item_number.text="1"
        holder.item_name.text=menuItem.getName()
        holder.item_price.text=menuItem.getPrice().toString()
    }

    override fun getItemCount(): Int {
        return cartArrayList.size
    }
    class CartViewHolder(itemview :View) :RecyclerView.ViewHolder(itemview) {
        val item_number :TextView
        val item_name :TextView
        val item_price :TextView
        val view=itemView
        init {
            item_number=itemview.findViewById(R.id.custom_cart_item_num)
            item_name=itemview.findViewById(R.id.custom_cart_item_name)
            item_price=itemview.findViewById(R.id.custom_cart_item_price)
        }

    }
    fun addItem(menuItem: menuItems){
        cartArrayList.add(menuItem)
    }

}