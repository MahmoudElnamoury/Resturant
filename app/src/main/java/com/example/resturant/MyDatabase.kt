package com.example.resturant

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper
import java.net.URI

class MyDatabase(
    context: Context?
) : SQLiteAssetHelper(context, DB_NAME, null, DB_VERSION) {
    companion object{
        public val DB_NAME:String="resturant.db"
        public val TABLE_NAME:String="menu"
        public val DB_VERSION :Int=1

        public val column_id :String="id"
        public val column_name :String ="name"
        public val column_details :String ="details"
        public val column_price :String ="price"

    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        super.onUpgrade(db, oldVersion, newVersion)
    }

}