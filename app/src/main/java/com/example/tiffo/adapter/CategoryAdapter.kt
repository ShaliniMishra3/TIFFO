package com.example.tiffo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tiffo.R
import com.example.tiffo.model.Category

class CategoryAdapter(private val list: List<Category>):
    RecyclerView.Adapter<CategoryAdapter.CategoryHolder>(){
        class CategoryHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txName: TextView= itemView.findViewById(R.id.txtName)
            val imgCategory: ImageView = itemView.findViewById(R.id.imgCategory)

        }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryAdapter.CategoryHolder{
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_category,parent,false)
        return CategoryHolder(view)
    }


    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {

        val item=list[position]
        holder.txName.text=item.name
        holder.imgCategory.setImageResource(item.image)

    }

    override fun getItemCount(): Int {
        return list.size
    }
}
