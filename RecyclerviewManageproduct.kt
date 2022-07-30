package com.example.farmbee

import android.content.Context
import android.provider.SyncStateContract.Helpers.update
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.farmbee.RecyclerviewManageproduct.ViewHolder
import kotlinx.android.synthetic.main.manageproduct_model.*
import kotlinx.android.synthetic.main.manageproduct_model.view.*
import org.w3c.dom.Text

class RecyclerviewManageproduct(val context: Context, val manageproduct : ArrayList<Manageproduct_fetch>): RecyclerView.Adapter<RecyclerviewManageproduct.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.manageproduct_model,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentitem = manageproduct[position]

        holder.edit_detail.text = currentitem.detail
        holder.edit_price.text = currentitem.price.toString()
        holder.edit_quantity.text = currentitem.quantity.toString()
        holder.spin.text = currentitem.spin
        holder.edit_title.text = currentitem.title
        holder.itemView.update.setOnClickListener {
            if (context is Manage_product) {
                context.update(position)
            }
        }
            holder.itemView.delete.setOnClickListener {
                if(context is Manage_product)
                {
                    context.delete(position)
                }
            }

        }



    override fun getItemCount(): Int {
       return manageproduct.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var edit_title : TextView
        var edit_detail : TextView
        var edit_price : TextView
        var edit_quantity :TextView
        var spin : TextView

        init {
            edit_title = itemView.findViewById(R.id.edit_title)
            edit_detail = itemView.findViewById(R.id.edit_detail)
            edit_quantity = itemView.findViewById(R.id.edit_quantity)
            edit_price = itemView.findViewById(R.id.edit_price)
            spin = itemView.findViewById(R.id.edit_spin)
        }
    }


}