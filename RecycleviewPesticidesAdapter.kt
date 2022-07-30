package com.example.farmbee

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cardlayout2.view.*
import kotlinx.android.synthetic.main.customer_recyclerview_model.view.*


class RecycleviewPesticidesAdapter(val context: Context, private val pesticidesFetch: ArrayList<Pesticides_fetch>) : RecyclerView.Adapter<RecycleviewPesticidesAdapter.ViewHolder>() {



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecycleviewPesticidesAdapter.ViewHolder {
        var v1 = LayoutInflater.from(parent.context).inflate(R.layout.cardlayout2, parent, false)
        return ViewHolder(v1)
    }

    override fun onBindViewHolder(holder: RecycleviewPesticidesAdapter.ViewHolder, position: Int) {
        val currentitem = pesticidesFetch[position]

        holder.itemtitle.text = currentitem.title
        holder.itemdetail.text = currentitem.detail
        holder.itemprice.text = currentitem.price.toString()

        //to open dialog box and show the product details and payment QR code
        holder.itemView.btn_fbuy.setOnClickListener {
            if(context is Pesticides_buy_activity)
                context.buypest(position)
        }

    }

    override fun getItemCount(): Int {
        return pesticidesFetch .size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemtitle:TextView
        var itemdetail:TextView
        var itemprice:TextView


        init {

            itemtitle = itemView.findViewById(R.id.itemtitle)
            itemdetail = itemView.findViewById(R.id.itemDetail)
            itemprice = itemView.findViewById(R.id.itemprice)

        }
    }

}