package com.example.resturant

class Category {
    var category_image : Int?=null
    var category_name : String?=null
    constructor(category_image: Int,category_name: String){
        this.category_image=category_image
        this.category_name=category_name
    }

    fun setCategoryImage(category_image :Int){
        this.category_image=category_image
    }
    fun getCategoryImage() :Int{
        return this.category_image!!
    }
    fun setCategoryName(category_name :String){
        this.category_name=category_name
    }
    fun getCategoryName() :String{
        return this.category_name!!
    }

}