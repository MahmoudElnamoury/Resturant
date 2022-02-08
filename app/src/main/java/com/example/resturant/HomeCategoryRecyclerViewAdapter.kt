package com.example.resturant

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HomeCategoryRecyclerViewAdapter : RecyclerView.Adapter<HomeCategoryRecyclerViewAdapter.CategoryViewHolder> {
    var categoryArrayList =ArrayList<Category>()
    var context :Context ?=null
    constructor(context: Context,categoryArrayList :ArrayList<Category>){
        this.categoryArrayList=categoryArrayList
        this.context=context
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.custom_home_categories,null,false)
        var holder= CategoryViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        var category :Category =categoryArrayList.get(position)
        holder.category_iv.setImageResource(category.getCategoryImage())
        holder.category_name.text=category.getCategoryName()
        holder.view.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                //move to menu activity
                var intent=Intent(context,Menu::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                //sending every category items if it exist  >> but this is expermnt activity that have only one category arrayList to show
                context!!.startActivity(intent)
            }

        })
    }

    override fun getItemCount(): Int {
        return categoryArrayList.size
    }
    class CategoryViewHolder (ItemView :View): RecyclerView.ViewHolder(ItemView) {
        var view =ItemView
        var category_iv :ImageView
        var category_name :TextView
        init {
              category_iv=itemView.findViewById(R.id.home_custom_categories_iv)
              category_name=itemView.findViewById(R.id.home_custom_categories_name_tv)
        }

    }

}