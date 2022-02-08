package com.example.resturant

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseAccess {
    var openHelper : SQLiteOpenHelper? = null
    var database : SQLiteDatabase?= null

    private constructor(context : Context){
        openHelper=MyDatabase(context)
    }
    //make this method and instance static
    companion object{
        private var instance :DatabaseAccess?=null
        public fun getInstance (context :Context) : DatabaseAccess?{
            if(instance==null)
                instance=DatabaseAccess(context)
            return instance
        }
    }

    // this method to get reference of readable database (or open database to read )
    fun open_DB(){
        database=this.openHelper!!.readableDatabase
    }
    //close database
    fun close_DB(){
        if (database!!.isOpen && database!=null)
            database!!.close()
    }
    /************************************************************************************************************/
    //get all menu items from database
     fun getMenu() : ArrayList<menuItems>{
        var menuArrayList =ArrayList<menuItems>()
        var cursor :Cursor= database!!.rawQuery("select * from " + MyDatabase.TABLE_NAME,null)
        if (cursor.moveToFirst() && cursor!=null){
            do {
                var id =cursor.getInt(cursor.getColumnIndex(MyDatabase.column_id))
                var name =cursor.getString(cursor.getColumnIndex(MyDatabase.column_name))
                var details =cursor.getString(cursor.getColumnIndex(MyDatabase.column_details))
                var price =cursor.getInt(cursor.getColumnIndex(MyDatabase.column_price))
                var menuItem :menuItems= menuItems(id,name,details,price)
                menuArrayList.add(menuItem)
            }
                while (cursor.moveToNext())
        }

        return menuArrayList
    }
    /************************************************************************************************************/
    //get all menu items from database that depending on search
    fun getMenu(search :String) :ArrayList<menuItems>{
        var menuArrayList=ArrayList<menuItems>()

        var cursor :Cursor=database!!.rawQuery("select * from " + MyDatabase.TABLE_NAME + " where " + MyDatabase.column_name +
                " like ? ", arrayOf("%"+search+"%"))
        if (cursor.moveToFirst() && cursor!=null)
        {
            do {
                var id =cursor.getInt(cursor.getColumnIndex(MyDatabase.column_id))
                var name =cursor.getString(cursor.getColumnIndex(MyDatabase.column_name))
                var details =cursor.getString(cursor.getColumnIndex(MyDatabase.column_details))
                var price =cursor.getInt(cursor.getColumnIndex(MyDatabase.column_price))
                var menuItem :menuItems= menuItems(id,name,details,price)
                menuArrayList.add(menuItem)
            }
                while (cursor.moveToNext())
        }
        return menuArrayList
    }
    /************************************************************************************************************/
    //    get specific menu item that depending on id
    fun getMenuItem(itemId :Int): menuItems? {
        var selectionArgs= arrayOf(""+itemId+"")
        var cursor :Cursor=database!!.rawQuery("select * from " +MyDatabase.TABLE_NAME+" where id = ? ",selectionArgs)
        if (cursor.moveToFirst()){
            var id =cursor.getInt(cursor.getColumnIndex(MyDatabase.column_id))
            var name =cursor.getString(cursor.getColumnIndex(MyDatabase.column_name))
            var details =cursor.getString(cursor.getColumnIndex(MyDatabase.column_details))
            var price =cursor.getInt(cursor.getColumnIndex(MyDatabase.column_price))
            var menuItem:menuItems=menuItems(id,name,details,price)
            return menuItem
        }
        return null
    }

}