package com.example.resturant

import android.view.ActionMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OrdersInnerRecyclerViewAdapter :
    RecyclerView.Adapter<OrdersInnerRecyclerViewAdapter.CustomOrderViewHolder> {
    var innerOrderItemArraylist =ArrayList<menuItems>()
    constructor(innerOrderArrayList: ArrayList<menuItems>){
        this.innerOrderItemArraylist=innerOrderArrayList
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomOrderViewHolder {
        //use the same custom view of cart recycler view in this adapter >> custom_cart_item
        var holder=CustomOrderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_cart_item,null,false))
        return holder
    }

    override fun onBindViewHolder(holder: CustomOrderViewHolder, position: Int) {
        var menuItem :menuItems=innerOrderItemArraylist.get(position)
        holder.item_num.text="1"
        holder.item_name.text=menuItem.getName()
        holder.item_price.text="$ "+menuItem.getPrice().toString()+".00"
    }

    override fun getItemCount(): Int {
        return innerOrderItemArraylist.size
    }

    class CustomOrderViewHolder (ItemView :View):RecyclerView.ViewHolder(ItemView){
        var item_num :TextView
        var item_name : TextView
        var item_price :TextView
        init {
            item_num=ItemView.findViewById(R.id.custom_cart_item_num)
            item_name=ItemView.findViewById(R.id.custom_cart_item_name)
            item_price=ItemView.findViewById(R.id.custom_cart_item_price)
        }

    }
}
