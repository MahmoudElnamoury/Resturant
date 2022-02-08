package com.example.resturant

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlin.collections.List

class Utils {
    companion object{
        //this method to moving cart activity
        fun moveToCart(context: Context){
            var intent=Intent(context,Cart::class.java)
        }

        fun navigationViewListener (activity: AppCompatActivity , navigationView: NavigationView,drawerLayout :DrawerLayout){
            navigationView.setNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.home ->{
                        if (drawerLayout.isDrawerOpen(GravityCompat.START) && activity==MainActivity()){
                            drawerLayout.closeDrawer(GravityCompat.START)
                        }
                        else{
                            val intent= Intent(activity, MainActivity::class.java)
                            activity.startActivity(intent)
                            drawerLayout.closeDrawer(GravityCompat.START)
                        }
                        return@setNavigationItemSelectedListener true
                    }
                    /**************************************************************************************/
                    R.id.menu ->{
                        if (drawerLayout.isDrawerOpen(GravityCompat.START) && activity==com.example.resturant.List()){
                            drawerLayout.closeDrawer(GravityCompat.START)
                            return@setNavigationItemSelectedListener true
                        }
                        else{
                            val intent= Intent(activity, com.example.resturant.List::class.java)
                            activity.startActivity(intent)
                            drawerLayout.closeDrawer(GravityCompat.START)
                        }
                        return@setNavigationItemSelectedListener true
                    }
                    /**************************************************************************************/
                    R.id.search ->{
                        if (drawerLayout.isDrawerOpen(GravityCompat.START) && activity==Search()){
                            drawerLayout.closeDrawer(GravityCompat.START)
                            return@setNavigationItemSelectedListener true
                        }
                        else{
                            val intent= Intent(activity,Search::class.java)
                            activity.startActivity(intent)
                            drawerLayout.closeDrawer(GravityCompat.START)
                        }
                        return@setNavigationItemSelectedListener true
                    }
                    /**************************************************************************************/
                    R.id.cart ->{
                        if (drawerLayout.isDrawerOpen(GravityCompat.START) && activity==Cart()){
                            drawerLayout.closeDrawer(GravityCompat.START)
                            return@setNavigationItemSelectedListener true
                        }
                        else{
                            val intent= Intent(activity,Cart::class.java)
                            activity.startActivity(intent)
                            drawerLayout.closeDrawer(GravityCompat.START)
                        }

                        return@setNavigationItemSelectedListener true
                    }
                    /**************************************************************************************/
                    R.id.orders ->{
                        if (drawerLayout.isDrawerOpen(GravityCompat.START) && activity==Orders()){
                            drawerLayout.closeDrawer(GravityCompat.START)
                            return@setNavigationItemSelectedListener true
                        }
                        else{
                            val intent= Intent(activity,Orders::class.java)
                            activity.startActivity(intent)
                            drawerLayout.closeDrawer(GravityCompat.START)
                        }
                        return@setNavigationItemSelectedListener true
                    }
                    /**************************************************************************************/
                    else ->{
                        Toast.makeText(activity,"there is no items like that", Toast.LENGTH_SHORT)
                        return@setNavigationItemSelectedListener false
                    }

                }
            }
        }
    }

}