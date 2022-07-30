package com.example.farmbee

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_fbuy.*
import kotlinx.android.synthetic.main.buydialog_customer.*
import kotlinx.android.synthetic.main.buydialog_customer.detail_buy
import kotlinx.android.synthetic.main.buydialog_customer.price_buy
import kotlinx.android.synthetic.main.buydialog_customer.title_buy
import kotlinx.android.synthetic.main.buydialog_farmer.*
import kotlinx.android.synthetic.main.cardlayout2.*


class Pesticides_buy_activity : AppCompatActivity() {
    private lateinit var dbref : DatabaseReference
    private lateinit var RecyclerViewPesticides : RecyclerView
    private lateinit var pesticideArrayList :ArrayList<Pesticides_fetch>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fbuy)
        RecyclerViewPesticides = findViewById(R.id.recyclerView)
        RecyclerViewPesticides.layoutManager = LinearLayoutManager(this)
        RecyclerViewPesticides.setHasFixedSize(true)
        pesticideArrayList = ArrayList()
        getProductData()


    }

    //menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menuitems, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        intent = Intent(this, Login::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        when (item.itemId) {
            R.id.logout -> startActivity(intent)

        }

        return super.onOptionsItemSelected(item)
    }

    private fun getProductData() {
        var flag=0
        dbref = FirebaseDatabase.getInstance().getReference("Pesticides")
        dbref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(Productsnapshot in snapshot.children){
                        val Pesticides = Productsnapshot.getValue(Pesticides_fetch::class.java)
                        Log.e("Test",Pesticides!!.title)
                        pesticideArrayList.add(Pesticides!!)
                        flag=1
                    }

                }
                if(flag==0)
                {
                    Toast.makeText(applicationContext,"Not Found!!", Toast.LENGTH_LONG).show();
                }
                refreshRecycle(pesticideArrayList)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun refreshRecycle(arr: ArrayList<Pesticides_fetch>)
    {

        var adpter=RecycleviewPesticidesAdapter(this,arr)
        RecyclerViewPesticides.adapter=adpter

    }

    //while clicking on buy button following code will show dialog box
    //recyclerview pesticide adapder is assosiated with it
    //buydialog_farmer is a model XML file,for click event of buy button
    fun buypest(position:Int){

        var dialog= Dialog(this)
        dialog.setContentView(R.layout.buydialog_farmer)
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window!!.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        lp.gravity = Gravity.CENTER
        dialog.window!!.attributes = lp

        dialog.show()
        var product=pesticideArrayList.get(position)

        dialog.title_buy.setText(product.title)
        dialog.detail_buy.setText(product.detail)
        dialog.price_buy.setText(product.price.toString())
        dialog.btnFpay.setOnClickListener {


            val packageName = "com.google.android.apps.nbu.paisa.user"
            val intent = packageManager.getLaunchIntentForPackage(packageName)
            startActivity(intent)

            dialog.dismiss()

        }
        refreshRecycle(pesticideArrayList)
    }
}



