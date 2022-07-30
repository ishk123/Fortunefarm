package com.example.farmbee

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class RecycleviewContactAdapter:RecyclerView.Adapter<RecycleviewContactAdapter.ViewHolder>() {
    //For Advice page
    private var titles1 = arrayOf("Mittal Skula","Nisha Patel","Rahul Patel","Neel Patel","Jagdish Panchal","S.J. Solanki","Prakash Shrivastav")
    private var details1 = arrayOf("7202035542","8469746155","9870574634","9870574634","7658976545","9426833912","9034567349")
    private var images1 = intArrayOf(R.drawable.pass1,R.drawable.pass2,R.drawable.pass3,R.drawable.pass4,R.drawable.pass5,R.drawable.pass1,R.drawable.pass5)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleviewContactAdapter.ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.cardlayout1,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecycleviewContactAdapter.ViewHolder, position: Int) {
        holder.itemtitle1.text = titles1[position]
        holder.itemDetail1.text = details1[position]
        holder.itemimage1.setImageResource(images1[position])

    }

    override fun getItemCount(): Int {
        return titles1.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemimage1: ImageView
        var itemtitle1: TextView
        var itemDetail1: TextView
        init {
            itemimage1 = itemView.findViewById<ImageView>(R.id.itemimage1)
            itemtitle1 = itemView.findViewById<TextView>(R.id.itemtitle1)
            itemDetail1 = itemView.findViewById<TextView>(R.id.itemdetail1)
        }
    }


}