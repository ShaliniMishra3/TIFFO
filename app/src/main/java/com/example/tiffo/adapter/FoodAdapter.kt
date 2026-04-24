package com.example.tiffo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tiffo.R
import com.example.tiffo.model.FoodItem

class FoodAdapter(private val list: List<FoodItem>, private val horizontal:Boolean):
    RecyclerView.Adapter<FoodAdapter.VH>(){

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitle: TextView = itemView.findViewById(R.id.txtTitle)
        val txtDesc: TextView = itemView.findViewById(R.id.txtDesc)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {

        val layoutId = if (horizontal) {
            R.layout.item_near
        } else {
            R.layout.item_food
        }
        val view = LayoutInflater.from(parent.context)
            .inflate(layoutId, parent, false)

        return VH(view)
    }
    override fun onBindViewHolder(holder: VH, position: Int) {
           val item=list[position]
           holder.txtTitle.text=item.title
           holder.txtDesc.text= item.desc
      }
    override fun getItemCount(): Int {
       return list.size
    }
}