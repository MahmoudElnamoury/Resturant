package com.example.resturant

import android.R.attr.description
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView


class Details : AppCompatActivity() {
//    lateinit var drawerLayout: DrawerLayout
//    lateinit var nav_view :NavigationView
    lateinit var back_btn : TextView
    lateinit var head_name : TextView
    lateinit var item_name:TextView
    lateinit var item_details: TextView
    lateinit var item_price :TextView
    lateinit var item_iv :ImageView
    lateinit var add : Button
    lateinit var databaseAccess :DatabaseAccess
    var item_key :String=""
    companion object{
        var itemId_key :String="itemId"
    }
    var itemId :Int ?=null
    var menuItem :menuItems?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        //inflating views
        back_btn = findViewById(R.id.details_back_btn)
        head_name=findViewById(R.id.details_tv_headName)
        item_name = findViewById(R.id.details_item_name_tv)
        item_iv = findViewById(R.id.details_item_iv)
        item_details = findViewById(R.id.details_item_details_tv)
        item_price = findViewById(R.id.details_item_price_tv)
        add = findViewById(R.id.details_item_add_btn)

        /*************************************************************************************************************/
        //get the intent that open that activity
        var intent = this.intent
        //get the bundle that passed through he intent
        val extras = intent.extras

        //check which bundle that comes from activities by check the key that bundle contain
        if (extras!!.containsKey("home_intent_v1")) {
            menuItem = extras!!.getSerializable("best deals details") as menuItems?
            setData(menuItem!!)
        }
        //if the bundle come from main activity intent
        else if (extras!!.containsKey("home_intent_v2")) {
            menuItem = extras!!.getSerializable("most popular details") as menuItems
            setData(menuItem!!)
        }
        //if the bundle come from main activity intent
        else if (extras!!.containsKey("list_intent")) {
            menuItem = extras.getSerializable("list item") as menuItems
            setData(menuItem!!)
        }
        //if the bundle come from search activity intent
        else if (extras!!.containsKey("search_intent")) {
            var item_id = extras.getInt(Menu.ITEM_KEY)
            databaseAccess = DatabaseAccess.getInstance(baseContext)!!
            databaseAccess.open_DB()
            menuItem = databaseAccess.getMenuItem(item_id)
            databaseAccess.close_DB()
            setData(menuItem!!)
        }
        //if the bundle come from menu activity intent
        else if (extras!!.containsKey("menu_intent")) {
            menuItem = extras.getSerializable(Menu.ITEM_KEY) as menuItems
            setData(menuItem!!)
        }

        //get data that came from search details activity
        itemId = intent.getIntExtra(Menu.ITEM_KEY, 0)
        //open database to get items
        databaseAccess = DatabaseAccess.getInstance(baseContext)!!
        databaseAccess.open_DB()
        menuItem = databaseAccess.getMenuItem(itemId!!)
        databaseAccess.close_DB()

        //set content to these views
        if (itemId != null && itemId!! > 0) {
            setData(menuItem!!)
        }
        /*************************************************************************************************************/

//        listener of back button
        back_btn.setOnClickListener {
            finish()
        }

//        listener of add button
        add.setOnClickListener {
            var intent = Intent(this, Cart::class.java)
            var item_name: String = item_name.text.toString()
            var item_price = Integer.parseInt(item_price.text.toString())
            menuItem = menuItems(null, item_name, null, item_price)
            intent.putExtra("details_intent","details")
            intent.putExtra("item", menuItem)
            startActivity(intent)

        }
    }

    // fill data to the views
    fun setData(menuItem: menuItems){
        //set local image because no image in database of items
        head_name.text=menuItem.getName()
        item_iv.setImageResource(R.drawable.mac_royal)
        item_name.text=menuItem.getName()
        item_details.text=menuItem.getDetails()
        item_price.text=menuItem.getPrice().toString()
    }
}