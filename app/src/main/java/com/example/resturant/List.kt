package com.example.resturant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import kotlin.collections.List

class List : AppCompatActivity() {
    //initialize variables
    lateinit var list_drawer_layout :DrawerLayout
    lateinit var listNavView: NavigationView
    lateinit var drawer_btn: ImageView
    lateinit var list_rv: RecyclerView
    lateinit var database: DatabaseAccess
    lateinit var list_array: ArrayList<menuItems>
    lateinit var list_adapter: ListRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        // inflating items
        list_drawer_layout=findViewById(R.id.list_drawer_layout)
        listNavView=findViewById(R.id.list_main_nav_view)
        drawer_btn=findViewById(R.id.list_drawer_btn)
        list_rv = findViewById(R.id.list_rv)
        //get arrayList from database
        database = DatabaseAccess.getInstance(baseContext)!!
        database.open_DB()
        list_array = database.getMenu()
        database.close_DB()

        //mange the adapter & the recyclerview
        list_adapter = ListRecyclerViewAdapter(list_array,baseContext)
        var layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        list_rv.layoutManager=layoutManager
        list_rv.adapter = list_adapter
        list_adapter.notifyDataSetChanged()
        list_rv.setHasFixedSize(true)

        //on click listener of btn drawer
        drawer_btn.setOnClickListener {
            list_drawer_layout.openDrawer(GravityCompat.START)
        }

        //listener of navigation view
        navigationViewHandler()
//        Utils.navigationViewListener(this,listNavView,list_drawer_layout)
    }

    //system  back button
    override fun onBackPressed() {
        if(list_drawer_layout.isDrawerOpen(GravityCompat.START)){
            list_drawer_layout.closeDrawer(GravityCompat.START)
        }
        else finish()

    }

    //manage navigation view
    fun navigationViewHandler(){
        listNavView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home ->{
                    val intent=Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    list_drawer_layout.closeDrawer(GravityCompat.START)
                    return@setNavigationItemSelectedListener true
                }
                R.id.menu ->{
                    list_drawer_layout.closeDrawer(GravityCompat.START)
                    return@setNavigationItemSelectedListener true
                }
                R.id.search ->{
                    val intent=Intent(this,Search::class.java)
                    startActivity(intent)
                    list_drawer_layout.closeDrawer(GravityCompat.START)
                    return@setNavigationItemSelectedListener true
                }
                R.id.cart ->{
                    val intent=Intent(this,Cart::class.java)
                    startActivity(intent)
                    list_drawer_layout.closeDrawer(GravityCompat.START)
                    return@setNavigationItemSelectedListener true
                }
                R.id.orders ->{
                    val intent=Intent(this,Orders::class.java)
                    startActivity(intent)
                    list_drawer_layout.closeDrawer(GravityCompat.START)
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