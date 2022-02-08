package com.example.resturant

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OrdersRecyclerViewAdapter :RecyclerView.Adapter<OrdersRecyclerViewAdapter.OrderViewHolder> {
    lateinit var context :Context
    var orderArralist=ArrayList<OrderArrayItems>()
    var order_index :Int?=null
    var order_price :String?=null

    constructor(orderArrayList: ArrayList<OrderArrayItems> , order_price :String){
        this.orderArralist=orderArrayList
        this.order_price=order_price
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        //get the context of the parent
        context=parent.context
        //inflate the custom view that i 'll pass to view holder
        var view=LayoutInflater.from(parent.context).inflate(R.layout.custom_order,null,false)
        var holder =OrderViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        order_index=position
        var orderArrayItems=orderArralist.get(position)
        holder.order_image.setImageResource(R.drawable.mac_royal)

        //handle the inner recycler View and set the content of it
        var inner_rv_adapter=OrdersInnerRecyclerViewAdapter(orderArrayItems.getInnerArray())
        var inner_layoutmanger  = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        holder.order_rv.adapter=inner_rv_adapter
        holder.order_rv.layoutManager=inner_layoutmanger
        inner_rv_adapter.notifyDataSetChanged()

        //sum the meal price that exist in inner array
        var final_meal_price :Int=0
        for (i:Int in 0 until orderArrayItems.getInnerArray().size){
            final_meal_price += orderArrayItems.getInnerArray().get(i).getPrice()!!
        }

        //set the final price of order ( sum of prices of items that in inner array )
        holder.order_price.text="Total : $ " +final_meal_price.toString() +".00"
        //set listener of cancel Btn
        holder.order_reorder.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                var orderArrayItem =orderArralist.get(order_index!!)
                orderArralist.add(orderArrayItem)
                notifyDataSetChanged()
            }
        })

        //set listener of reorder Button Btn
        holder.cancel_order.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                orderArralist.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeRemoved(position,itemCount)
                notifyDataSetChanged()
            }
        })
    }

    override fun getItemCount(): Int {
        return orderArralist.size
    }

    inner class OrderViewHolder (itemView :View): RecyclerView.ViewHolder(itemView) {
        var order_image : ImageView
        var order_rv : RecyclerView
        var order_price :TextView
        var order_reorder :Button
        var cancel_order :Button
        init {
            order_image=itemView.findViewById(R.id.custom_order_iv)
            order_rv=itemView.findViewById(R.id.custom_order_rv)
            order_price=itemView.findViewById(R.id.custom_order_tv_price)
            order_reorder=itemView.findViewById(R.id.custom_order_btn_reorder)
            cancel_order=itemView.findViewById(R.id.custom_order_btn_cancel_order)
        }


    }
}