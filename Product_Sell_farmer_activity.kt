package com.example.farmbee


import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

import kotlinx.android.synthetic.main.activity_fsell.*
import kotlinx.android.synthetic.main.manageproduct_model.*


class Product_Sell_farmer_activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fsell)

        var splogin = getSharedPreferences("splogin", Context.MODE_PRIVATE)
        btn_add.setOnClickListener {

            //Fire base code
            var database = FirebaseDatabase.getInstance().getReference("Product")
            var email = splogin.getString("email",null).toString()
            var title = ET_title.text.toString()
            var detail = ET_detail.text.toString()
            var quantity = ET_quantity.text.toString().toInt()
            var price = ET_price.text.toString().toInt()
            var choose = spin.selectedItem.toString()

            var product = ProductInfo(title,email,detail,quantity,price,choose)
            database.child(title).setValue(product).addOnSuccessListener {
                Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
            }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show()
                }

            clearfilds()
            spin.setSelection(0)
            Toast.makeText(this, "Product add for Customer", Toast.LENGTH_SHORT).show()

        }

        btn_manage_product.setOnClickListener {
            startActivity(Intent(this,Manage_product::class.java))
        }


    }



    fun clearfilds() {
        ET_title.setText("")
        ET_detail.setText("")
        ET_price.setText("")
        ET_quantity.setText("")
    }

    //menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menuitems,menu)
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

