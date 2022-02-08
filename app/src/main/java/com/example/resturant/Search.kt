package com.example.resturant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import okhttp3.internal.Util
import kotlin.collections.List

class Search : AppCompatActivity() {
    //initialize variables
    lateinit var search_drawer_layout : DrawerLayout
    lateinit var search_nav_view : NavigationView
    lateinit var drawer_btn : ImageView
    lateinit var searchview :androidx.appcompat.widget.SearchView
    lateinit var search_rv : RecyclerView
    lateinit var databaseAccess :DatabaseAccess
    var searchArrayList =ArrayList<menuItems>()
    lateinit var myadapter :SearchRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        //inflating views
        search_drawer_layout=findViewById(R.id.search_drawer_layout)
        search_nav_view=findViewById(R.id.search_nav_view)
        drawer_btn=findViewById(R.id.search_drawer_btn)
        searchview=findViewById(R.id.search_searchView)
        search_rv=findViewById(R.id.search_meals_rv)

        //set submit button to search view
        searchview.isSubmitButtonEnabled()

        //deal with intent to send more item  to cart ( activity )
        var intent=this.intent
        var extras : Bundle? = intent.extras
//        var intent=Intent()
//        intent.putExtra("item",myadapter.getItem())
//        setResult(RESULT_OK,intent)


        //deal with recyclerView
        //open database to read
        databaseAccess=DatabaseAccess.getInstance(baseContext)!!
        databaseAccess.open_DB()
        searchArrayList=databaseAccess.getMenu()
        databaseAccess.close_DB()

        myadapter= SearchRecyclerViewAdapter(object :OnClickListener{
            override fun onClick(itemId: Int) {
//              dealing with the result of cart intent  >> add more item to the cart recyclerview
                if (extras != null) {
                    if (extras.containsKey("cart_intent")) {
                        databaseAccess = DatabaseAccess.getInstance(baseContext)!!
                        databaseAccess.open_DB()
                        var menuItem = databaseAccess.getMenuItem(itemId)
                        var intent = Intent()
                        intent.putExtra("item", menuItem)
                        setResult(RESULT_OK, intent)
                        databaseAccess.close_DB()
                        finish()
                    }
                }

                //listener of items in search adapter
                else {
                    var intent: Intent = Intent(baseContext, Details::class.java)
                    intent.putExtra("search_intent", "extra4")
                    intent.putExtra(Menu.ITEM_KEY, itemId)
                    startActivity(intent)
               }
            }
        },baseContext)

        myadapter.setItems(searchArrayList)
        search_rv.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        search_rv.adapter=myadapter
        myadapter.notifyDataSetChanged()

        //listener of search view   >>   search
        searchview.setOnQueryTextListener (object :androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!databaseAccess.database!!.isOpen){
                    databaseAccess.open_DB()
                }
                searchArrayList =databaseAccess.getMenu(query!!)
                myadapter.setItems(searchArrayList)
                myadapter.notifyDataSetChanged()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!databaseAccess.database!!.isOpen){
                    databaseAccess.open_DB()
                }
                searchArrayList =databaseAccess.getMenu(newText!!)
                databaseAccess.close_DB()
                myadapter.setItems(searchArrayList)
                myadapter.notifyDataSetChanged()
                return true
            }

        })

        //listener of search view   >>   close the search view
        searchview.setOnCloseListener(object :androidx.appcompat.widget.SearchView.OnCloseListener{
            override fun onClose(): Boolean {
                if (!databaseAccess.database!!.isOpen){
                    databaseAccess.open_DB()
                }
                searchArrayList =databaseAccess.getMenu()
                databaseAccess.close_DB()
                myadapter.setItems(searchArrayList)
                myadapter.notifyDataSetChanged()
                return true
            }

        })

        //on click listener of btn drawer
        drawer_btn.setOnClickListener{
            search_drawer_layout.openDrawer(GravityCompat.START)
        }

        //listener of navigation view
        navigationViewHandler()
//        Utils.navigationViewListener(this,search_nav_view,search_drawer_layout)
    }

    //system back button
    override fun onBackPressed() {
        if(search_drawer_layout.isDrawerOpen(GravityCompat.START)){
            search_drawer_layout.closeDrawer(GravityCompat.START)
        }
        else finish()
    }

    //outside onCreate method   >>  separated  methods
    //manage navigation view
    fun navigationViewHandler(){
        search_nav_view.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home ->{
                    val intent= Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    search_drawer_layout.closeDrawer(GravityCompat.START)
                    return@setNavigationItemSelectedListener true
                }
                R.id.menu ->{
                    val intent= Intent(this, com.example.resturant.List::class.java)
                    startActivity(intent)
                    search_drawer_layout.closeDrawer(GravityCompat.START)
                    return@setNavigationItemSelectedListener true
                }
                R.id.search ->{
                    search_drawer_layout.closeDrawer(GravityCompat.START)
                    return@setNavigationItemSelectedListener true
                }
                R.id.cart ->{
                    val intent= Intent(this,Cart::class.java)
                    startActivity(intent)
                    search_drawer_layout.closeDrawer(GravityCompat.START)
                    return@setNavigationItemSelectedListener true
                }
                R.id.orders ->{
                    val intent= Intent(this,Orders::class.java)
                    startActivity(intent)
                    search_drawer_layout.closeDrawer(GravityCompat.START)
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