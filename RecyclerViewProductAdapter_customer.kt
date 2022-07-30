package com.example.farmbee

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.customer_recyclerview_model.view.*

class RecyclerViewProductAdapter_customer(val context: Context, val productFetched : ArrayList<Product_fetch>) :RecyclerView.Adapter<RecyclerViewProductAdapter_customer.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.customer_recyclerview_model,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentitem = productFetched[position]

        holder.title.text = currentitem.title
        holder.detail.text = currentitem.detail
        holder.price.text = currentitem.price.toString()

        //to open dialog box and show the product details and payment QR code
        holder.itemView.btn_Cbuy.setOnClickListener {
            if(context is Customer_Home)
                context.buy(position)
        }

    }

    override fun getItemCount(): Int {
        return productFetched.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
     var title : TextView
     var detail : TextView
     var price : TextView


        init {
            title = itemView.findViewById(R.id.Citemtitle)
            detail = itemView.findViewById(R.id.CitemDetail)
            price = itemView.findViewById(R.id.Citemprice)

        }
    }
}