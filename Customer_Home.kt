package com.example.farmbee

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.buydialog_customer.*

class Customer_Home : AppCompatActivity() {


    private lateinit var dbref : DatabaseReference
    private lateinit var customerRecyclerView : RecyclerView
    private lateinit var productArrayList :ArrayList<Product_fetch>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_home)

        customerRecyclerView = findViewById(R.id.customer_reycyclerview)
        customerRecyclerView.layoutManager = LinearLayoutManager(this)
        customerRecyclerView.setHasFixedSize(true)

        productArrayList = arrayListOf()
        getProductData()


    }

    private fun getProductData() {
        var flag=0
        dbref = FirebaseDatabase.getInstance().getReference("Product")
        dbref.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(Productsnapshot in snapshot.children){
                        val product = Productsnapshot.getValue(Product_fetch::class.java)
                        Log.d("Test",product!!.title)
                        productArrayList.add(product!!)
                        flag=1
                    }

                }
                if(flag==0)
                {
                    Toast.makeText(applicationContext,"Not Found!!", Toast.LENGTH_LONG).show();
                }
                refreshRecycle(productArrayList)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
    //while clicking on buy button following code will show dialog box
    //recyclerviewproductadapder_customer is assosiated with it
    //buydialog_customer is a model XML file,for click event of buy button
    fun buy(position:Int){

        var dialog= Dialog(this)
        dialog.setContentView(R.layout.buydialog_customer)
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window!!.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        lp.gravity = Gravity.CENTER
        dialog.window!!.attributes = lp

        dialog.show()
        var product=productArrayList.get(position)

        dialog.title_buy.setText(product.title)
        dialog.detail_buy.setText(product.detail)
        dialog.price_buy.setText(product.price.toString())
        dialog.btnCpay.setOnClickListener {

            val packageName = "com.google.android.apps.nbu.paisa.user"
            val intent = packageManager.getLaunchIntentForPackage(packageName)
            startActivity(intent)

            dialog.dismiss()


        }
        refreshRecycle(productArrayList)

    }

    fun refreshRecycle(arr: ArrayList<Product_fetch>)
    {

        var adpter=RecyclerViewProductAdapter_customer(this,arr)
        customerRecyclerView.adapter=adpter

    }


    //menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menuitems, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        intent = Intent(this,Login::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        when (item.itemId) {
            R.id.logout -> startActivity(intent)

        }

        return super.onOptionsItemSelected(item)
    }


}