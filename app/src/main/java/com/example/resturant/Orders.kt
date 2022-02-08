package com.example.resturant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView

class Orders : AppCompatActivity() {
    lateinit var orders_drawer_layout :DrawerLayout
    lateinit var orders_nav_view : NavigationView
    lateinit var drawer_btn : ImageView
    companion object{
        var orderArrayList =ArrayList<OrderArrayItems>()
    }
    var innerOrderArraylist =ArrayList<menuItems>()
    var final_order_price :String?=null
    lateinit var orders_rv :RecyclerView
    lateinit var orderAdapter :OrdersRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)
        //inflating views
        orders_drawer_layout=findViewById(R.id.orders_drawer_layout)
        drawer_btn=findViewById(R.id.reservation_drawer_btn)
        orders_nav_view=findViewById(R.id.orders_nav_view)
        orders_rv=findViewById(R.id.orders_rv)

        //get data that comes from intent
        var intent =this.intent
        try {
            innerOrderArraylist= intent.getSerializableExtra("passed cart Array")!! as ArrayList<menuItems>
        }
        catch (e :Exception){
        }

        final_order_price=(intent.getStringExtra("final price").toString())!!

        //check if order inner array is empty  >> make the outer array of order activity clear
        if (innerOrderArraylist.size==0){
            orderArrayList.clear()
        }
        orderArrayList.add(OrderArrayItems(null,innerOrderArraylist, final_order_price!!))


        //handle the orders_rv
        orderAdapter= OrdersRecyclerViewAdapter(orderArrayList, final_order_price!!)
        var layoutManager=LinearLayoutManager(baseContext,LinearLayoutManager.VERTICAL,false)
        orders_rv.layoutManager=layoutManager
        orders_rv.adapter=orderAdapter
        orderAdapter.notifyDataSetChanged()

        //on click listener of btn drawer
        drawer_btn.setOnClickListener{
            orders_drawer_layout.openDrawer(GravityCompat.START)
        }

        // calling listener for navigation  view
//        Utils.navigationViewListener(this,navDrawer)
        navigationViewHandler()
    }

    //system back button
    override fun onBackPressed() {
        if(orders_drawer_layout.isDrawerOpen(GravityCompat.START)){
            orders_drawer_layout.closeDrawer(GravityCompat.START)
        }
        else finish()
    }

    //listener of navigation view
    fun navigationViewHandler(){
        orders_nav_view.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home ->{
                    val intent= Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    orders_drawer_layout.closeDrawer(GravityCompat.START)
                    return@setNavigationItemSelectedListener true
                }
                R.id.menu ->{
                    val intent= Intent(this, com.example.resturant.List::class.java)
                    startActivity(intent)
                    orders_drawer_layout.closeDrawer(GravityCompat.START)
                    return@setNavigationItemSelectedListener true
                }
                R.id.search ->{
                    val intent= Intent(this,Search::class.java)
                    startActivity(intent)
                    orders_drawer_layout.closeDrawer(GravityCompat.START)
                    return@setNavigationItemSelectedListener true
                }
                R.id.cart ->{
                    val intent= Intent(this,Cart::class.java)
                    startActivity(intent)
                    orders_drawer_layout.closeDrawer(GravityCompat.START)
                    return@setNavigationItemSelectedListener true
                }
                R.id.orders ->{
                    orders_drawer_layout.closeDrawer(GravityCompat.START)
                    return@setNavigationItemSelectedListener true
                }
                else ->{
                    Toast.makeText(this,"there is no items like that", Toast.LENGTH_SHORT)
                    return@setNavigationItemSelectedListener false
                }

            }
        }
    }

}