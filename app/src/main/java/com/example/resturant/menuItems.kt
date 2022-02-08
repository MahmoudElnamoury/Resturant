package com.example.resturant

import android.os.Parcelable
import java.io.Serializable

class menuItems() :Serializable {
    private var id :Int?=null
    private var name :String?=null
    private var details :String?=null
    private var price :Int?=null

    constructor(id: Int?, name: String?, details: String?, price: Int) : this() {
        this.id = id
        this.name = name
        this.details = details
        this.price = price
    }

    fun setId(id :Int){
        this.id=id
    }
    fun getId() : Int? {
        return this.id
    }
    fun setName(name :String){
        this.id=id
    }
    fun getName() : String? {
        return this.name
    }
    fun setDetails(details :String){
        this.details=details
    }
    fun getDetails() : String? {
        return this.details
    }
    fun setPrice(price :Int){
        this.price=price
    }
    fun getPrice() : Int? {
        return this.price
    }

}