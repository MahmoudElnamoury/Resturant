package com.example.resturant

class OrderArrayItems {
    private var image : Int?=null
    private var innerArray =ArrayList<menuItems>()
    private var final_price :String?=null
    constructor(image: Int?, innerArray: ArrayList<menuItems>,final_price :String){
        this.image=image
        this.innerArray=innerArray
        this.final_price=final_price
    }

    fun setImage (image :Int) {
        this.image=image
    }
    fun getImage () :Int{
       return image!!
    }
    fun setInnerArray (innerArray :ArrayList<menuItems>){
        this.innerArray=innerArray
    }
    fun getInnerArray () :ArrayList<menuItems>{
        return innerArray
    }
    fun setFinalPrice (final_price: String){
        this.final_price=final_price
    }
    fun getFinalPrice () :String{
        return final_price!!
    }

}