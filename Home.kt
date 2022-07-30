package com.example.farmbee

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        btn_buyproduct.setOnClickListener {
           startActivity(Intent(this,Pesticides_buy_activity::class.java))
        }

        btn_sellproduct.setOnClickListener {
            startActivity(Intent(this,Product_Sell_farmer_activity::class.java))
        }

        btn_askadvice.setOnClickListener {
            startActivity(Intent(this,FAdvice_activity::class.java))
        }


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
