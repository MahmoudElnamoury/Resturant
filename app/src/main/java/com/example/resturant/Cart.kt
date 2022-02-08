package com.example.resturant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ActionMode
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Cart : AppCompatActivity() {
    lateinit var back:TextView
    lateinit var cart_rv :RecyclerView
    lateinit var btn_add_more :Button
    lateinit var total_price :TextView
    lateinit var btn_order:Button
    var databaseAccess :DatabaseAccess?=null
    var cartArrayList =ArrayList<menuItems>()
    var cartAdapter :CartRecyclerViewAdapter?=null
    lateinit var menuItem: menuItems
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        //inflating views
        back=findViewById(R.id.cart_back)
        cart_rv=findViewById(R.id.cart_rv)
        btn_add_more=findViewById(R.id.cart_btn_add_item)
        total_price=findViewById(R.id.cart_total_price)
        btn_order=findViewById(R.id.cart_place_order)
//        order_btn=findViewById(R.id.cart_btn)

        //handle with views

//set adapter to recycler view
        cartAdapter=CartRecyclerViewAdapter(cartArrayList,this)
        var layoutManager=LinearLayoutManager(baseContext,LinearLayoutManager.VERTICAL,false)
        cart_rv.layoutManager= layoutManager
        cart_rv.adapter=cartAdapter

        //get the intent that open the activity
        var intent :Intent=this.intent
        //check if intent bundle that comes from details activity contain that key( details key ) or not
        var extras=intent.extras
        try {
            if(extras!!.containsKey("details_intent")) {
                menuItem = intent.getSerializableExtra("item") as menuItems
                cartArrayList.add(menuItem)
                cartAdapter!!.notifyDataSetChanged()
            }
        }
        catch (e : Exception){

        }


//        handler of start activity for result
        var arl :ActivityResultLauncher <Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            object : ActivityResultCallback<ActivityResult>{
                override fun onActivityResult(result: ActivityResult?) {
                    if (result != null) {
                        if (result.resultCode == RESULT_OK){
                            var menuItem= result!!.data!!.extras!!.getSerializable("item") as menuItems
                            cartAdapter!!.addItem(menuItem)
                            cartAdapter!!.notifyDataSetChanged()

                            //set the final price of the order
                            var final_price : Int = 0
                            for (item_index :Int in 0 until cartArrayList.size)
                            {
                                final_price = final_price + cartArrayList.get(item_index).getPrice()!!
                            }
                            //set text of price textView
                            total_price.text="Total : " + final_price
                        }
                    }
                }
            }
        )

//       set listener of add more btn
        btn_add_more.setOnClickListener{
            var intent=Intent(baseContext,Search::class.java)
            intent.putExtra("cart_intent","add more")
            arl.launch(intent)
        }

//        set the final price of the order
        var final_price : Int = 0
        for (item_index :Int in 0 until cartArrayList.size)
        {
            final_price = final_price + cartArrayList.get(item_index).getPrice()!!
        }
        //set text of price textView
        total_price.text="Total : " + final_price


        //listener of order button
        btn_order.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                if (cartArrayList==null){
                    Toast.makeText(baseContext,"please insert your order",Toast.LENGTH_LONG).show()
                }
                var intent = Intent(baseContext,Orders::class.java)
                intent.putExtra("passed cart Array",cartArrayList)
                intent.putExtra("final price",total_price.text)
                startActivity(intent)
                //make the array empty after passing the array to orders activity
                //cartArrayList.removeAll(cartArrayList)   >>    another way to make the array empty
                cartArrayList.clear()
            }
        })

        //back button
        back.setOnClickListener{
            finish()
        }

    }


}