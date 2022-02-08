package com.example.resturant

import android.content.Context
import android.content.Intent
import android.icu.number.IntegerWidth
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.List

class MainActivity : AppCompatActivity() {
    //initialize variables
    lateinit var databaseAccess :DatabaseAccess
//    var menuArrayList =ArrayList<menuItems>()
//    lateinit var myadapter :HomeRecyclerViewAdapter
    lateinit var home_drawer :DrawerLayout
    lateinit var home_nav_view :NavigationView
    lateinit var drawer_btn :ImageView
    lateinit var categories_rv :RecyclerView
    var categoryArrayList=ArrayList<Category>()
    lateinit var best_deals :FrameLayout
    lateinit var best_deal_name :TextView
    lateinit var most_popular_iv :ImageView
    lateinit var most_popular_tv_name :TextView
    lateinit var most_popular_tv_price :TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //inflating views
        home_drawer=findViewById(R.id.home_drawer_layout)
        home_nav_view=findViewById(R.id.home_nav_view)
        drawer_btn=findViewById(R.id.home_drawer_btn)
        categories_rv=findViewById(R.id.home_categories_rv)
        best_deals=findViewById(R.id.home_best_deal_fl)
        best_deal_name=findViewById(R.id.home_best_deal_name_tv)
        most_popular_iv=findViewById(R.id.home_most_popular_iv)
        most_popular_tv_name=findViewById(R.id.home_most_popular_name_tv)
        most_popular_tv_price=findViewById(R.id.home_most_popular_price_tv)

        //deal with recyclerView
        // intialize category ArrayList
        categoryArrayList.add(Category(R.drawable.mac_royal,"breakfast"))
        categoryArrayList.add(Category(R.drawable.mac_royal,"launch"))
        categoryArrayList.add(Category(R.drawable.mac_royal,"sandwiches"))
        categoryArrayList.add(Category(R.drawable.mac_royal,"dinner"))
        categoryArrayList.add(Category(R.drawable.mac_royal,"others"))

        //set ArrayList to adapter
        var categoryAdapter=HomeCategoryRecyclerViewAdapter(baseContext,categoryArrayList)
        var layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        categories_rv.adapter=categoryAdapter
        categories_rv.layoutManager=layoutManager

//       listener of btn drawer
        drawer_btn.setOnClickListener{
            home_drawer.openDrawer(GravityCompat.START)
        }

//       listener of FrameLayout
        best_deals.setOnClickListener {
            var intent=Intent(baseContext,Details::class.java)
            var menuItem =menuItems(null,best_deal_name.text.toString(),"meat , union ,tomato.....", 20)
            intent.putExtra("home_intent_v1","extra1")
            intent.putExtra("best deals details",menuItem)
            startActivity(intent)
        }

//       listener of best deals
        most_popular_iv.setOnClickListener {
            var intent=Intent(baseContext,Details::class.java)
            var most_popular_name=most_popular_tv_name.text.toString()
            var most_popular_price=Integer.parseInt(most_popular_tv_price.text.toString())
            var menuItem =menuItems(null,most_popular_name,"hot salad , union ,tomato.....", most_popular_price)
             intent.putExtra("home_intent_v2","extra2")
            intent.putExtra("most popular details",menuItem)
            startActivity(intent)
        }

//       listener of navigation view
        navigationViewHandler()
//        Utils.navigationViewListener(this,home_nav_view,home_drawer_layout)

        }

    //system back button
    override fun onBackPressed() {
        if(home_drawer.isDrawerOpen(GravityCompat.START)){
            home_drawer.closeDrawer(GravityCompat.START)
        }
        else finish()
    }

    //manage navigation view
    fun navigationViewHandler(){
        home_nav_view.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home ->{
                    home_drawer.closeDrawer(GravityCompat.START)
                    return@setNavigationItemSelectedListener true
                }
                R.id.menu ->{
                    val intent=Intent(this,com.example.resturant.List::class.java)
                    startActivity(intent)
                    home_drawer.closeDrawer(GravityCompat.START)
                    return@setNavigationItemSelectedListener true
                }
                R.id.search ->{
                    val intent=Intent(this,Search::class.java)
                    startActivity(intent)
                    home_drawer.closeDrawer(GravityCompat.START)
                    return@setNavigationItemSelectedListener true
                }
                R.id.cart ->{
                    val intent=Intent(this,Cart::class.java)
                    startActivity(intent)
                    home_drawer.closeDrawer(GravityCompat.START)
                    return@setNavigationItemSelectedListener true
                }
                R.id.orders ->{
                    val intent=Intent(this,Orders::class.java)
                    startActivity(intent)
                    home_drawer.closeDrawer(GravityCompat.START)
                    return@setNavigationItemSelectedListener true
                }
                else ->{
                    Toast.makeText(this,"there is no items like that",Toast.LENGTH_SHORT)
                    return@setNavigationItemSelectedListener false
                }

            }
        }
    }
}