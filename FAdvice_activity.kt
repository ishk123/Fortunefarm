package com.example.farmbee

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_fadvice.*


class FAdvice_activity : AppCompatActivity() {
    private var lauoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecycleviewContactAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fadvice)

        lauoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,true)
        lauoutManager = LinearLayoutManager(this)
        recyclerView1.layoutManager = lauoutManager

        adapter = RecycleviewContactAdapter()
        recyclerView1.adapter = adapter



    }


    //menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menuitems,menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.logout -> startActivity(Intent(this, Login::class.java))

        }

        return super.onOptionsItemSelected(item)
    }
}