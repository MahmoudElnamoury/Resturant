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
import kotlin.collections.List

class Menu : AppCompatActivity() {
    lateinit var menu_drawer : DrawerLayout
    lateinit var drawer_btn : ImageView
    lateinit var navDrawer : NavigationView
    lateinit var menu_rv :RecyclerView
    lateinit var myadapter :MenuRecyclerViewAdapter
    var menuArrayList =ArrayList<menuItems>()
    lateinit var databaseAccess :DatabaseAccess
    companion object{
        var ITEM_KEY :String ="item_key"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        //inflating views
        menu_rv=findViewById(R.id.menu_items)
        drawer_btn=findViewById(R.id.menu_drawer_btn)
        menu_drawer=findViewById(R.id.menu_drawer_layout)
        navDrawer=findViewById(R.id.menu_nav_view)

        //handel the recyclerview
        //open database
        databaseAccess=DatabaseAccess.getInstance(baseContext)!!
        databaseAccess.open_DB()
        menuArrayList=databaseAccess.getMenu()
        databaseAccess.close_DB()
        myadapter= MenuRecyclerViewAdapter(object :OnClickListener{
            override fun onClick(itemId: Int){
                //listener of menu recycler view
                //get the item from database depending on its id
                databaseAccess.open_DB()
                var menuItem=databaseAccess.getMenuItem(itemId)
                databaseAccess.close_DB()
                //pass the item to details activity through intent
                var intent :Intent=Intent(baseContext,Details::class.java)
                intent.putExtra(ITEM_KEY,menuItem)
                intent.putExtra("menu_intent","extra5")
                startActivity(intent)
            }
        })

        //set array list to adapter
        myadapter.setMenuArrayList(menuArrayList)
        menu_rv.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        menu_rv.adapter=myadapter
        myadapter.notifyDataSetChanged()

        //listener of drawer button
        drawer_btn.setOnClickListener{
            menu_drawer.openDrawer(GravityCompat.START)
        }

        // listener for navigation  view
        navigationViewHandler()
    }

    // back system button
    override fun onBackPressed() {
        if (menu_drawer.isDrawerOpen(GravityCompat.START))
            menu_drawer.closeDrawer(GravityCompat.START)
        else finish()
    }

    // listener for navigation  view
    fun navigationViewHandler(){
        navDrawer.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home ->{
                    val intent= Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    return@setNavigationItemSelectedListener true
                }
                R.id.menu ->{
                    menu_drawer.closeDrawer(GravityCompat.START)
                    return@setNavigationItemSelectedListener true
                }
                R.id.search ->{
                    val intent= Intent(this,Search::class.java)
                    startActivity(intent)
                    return@setNavigationItemSelectedListener true
                }
                R.id.cart ->{
                    val intent= Intent(this,Cart::class.java)
                    startActivity(intent)
                    return@setNavigationItemSelectedListener true
                }
                R.id.orders ->{
                    val intent= Intent(this,Orders::class.java)
                    startActivity(intent)
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