package com.example.farmbee

import android.app.Dialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.manageproduct_model.*
import kotlinx.android.synthetic.main.manageproduct_model.edit_detail
import kotlinx.android.synthetic.main.manageproduct_model.edit_price
import kotlinx.android.synthetic.main.manageproduct_model.edit_quantity
import kotlinx.android.synthetic.main.manageproduct_model.edit_spin
import kotlinx.android.synthetic.main.manageproduct_model.edit_title
import kotlinx.android.synthetic.main.update_layout.*


class Manage_product : AppCompatActivity() {

    //lateinit var arr:ArrayList<Manageproduct_fetch>
    private lateinit var dbref : DatabaseReference
    private lateinit var RecyclerviewManageproduct : RecyclerView
    private lateinit var manageproductArrayList :ArrayList<Manageproduct_fetch>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_product)

        RecyclerviewManageproduct = findViewById(R.id.recyclerview_manage)
        RecyclerviewManageproduct.layoutManager = LinearLayoutManager(this)
        RecyclerviewManageproduct.setHasFixedSize(true)
        manageproductArrayList = arrayListOf()
        getProductData()


    }

    private fun getProductData() {
        var flag=0
        dbref = FirebaseDatabase.getInstance().getReference("Product")
        dbref.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if(snapshot.exists()){
                    manageproductArrayList.clear()
                    for(Productsnapshot in snapshot.children){

                        val product = Productsnapshot.getValue(Manageproduct_fetch::class.java)
                        Log.d("Test",product!!.email)

                        var splogin = getSharedPreferences("splogin", Context.MODE_PRIVATE)
                        var id = splogin.getString("email",null)
                        Log.d("Farmerid",id.toString())
                        Log.d("Product email",product.email)
                        if(id.toString() == product.email ){
                            Log.d("Test",product!!.email)
                            manageproductArrayList.add(product!!)
                            flag=1
                        }


                    }
                    if(flag==0)
                    {
                        Toast.makeText(applicationContext,"Not Found!!",Toast.LENGTH_LONG).show();
                    }
                    refreshRecycle(manageproductArrayList)

                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


    }
    fun refreshRecycle(arr: ArrayList<Manageproduct_fetch>)
    {

        var adpter=RecyclerviewManageproduct(this,arr)
        RecyclerviewManageproduct.adapter=adpter

    }

    fun update(position:Int)
    {
        var dialog= Dialog(this)
        dialog.setContentView(R.layout.update_layout)
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window!!.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        lp.gravity = Gravity.CENTER
        dialog.window!!.attributes = lp

        dialog.show()
        var product=manageproductArrayList.get(position)
        var key=product.title
        dialog.edit_title.setText(product.title)
        dialog.edit_detail.setText(product.detail)
        dialog.edit_price.setText(product.price.toString())
        dialog.edit_quantity.setText(product.quantity.toString())
        dialog.edit_spin.setText(product.spin)
        dialog.btn_update.setOnClickListener {

            var splogin = getSharedPreferences("splogin", Context.MODE_PRIVATE)
            var title=dialog.edit_title.text.toString()
            var email = splogin.getString("email",null).toString()
            var detail=dialog.edit_detail.text.toString()
            var price=dialog.edit_price.text.toString().toInt()
            var quantity=dialog.edit_quantity.text.toString().toInt()
            var choose=dialog.edit_spin.text.toString()

            var product=ProductInfo(title,email,detail,quantity,price,choose)

            var db=FirebaseDatabase.getInstance().getReference("Product")
            Log.d("key",key.toString())
            db.child(key).setValue(product).addOnSuccessListener {
                Toast.makeText(this,"Record Updated successfully",Toast.LENGTH_LONG).show()
            }
            dialog.dismiss()
        }
        refreshRecycle(manageproductArrayList)
    }

    fun delete(position: Int)
    {
        var product=manageproductArrayList.get(position)
        var key=product.title
        var db=FirebaseDatabase.getInstance().getReference("Product")
        db.child(key).setValue(null).addOnSuccessListener {
            Toast.makeText(this,"Record deleted successfully",Toast.LENGTH_LONG).show()
        }
        getProductData()
        refreshRecycle(manageproductArrayList)

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
            R.id.logout-> {
                startActivity(intent)
                var splogin = getSharedPreferences("login", Context.MODE_PRIVATE)
                var cleardata = splogin.edit()
                cleardata.apply() {
                    cleardata.clear()
                }.apply()
            }

        }

        return super.onOptionsItemSelected(item)
    }

}