package com.example.tiffo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tiffo.R
import com.example.tiffo.model.OnboardingItem

class OnboardingAdapter(
    private val list:List<OnboardingItem>
): RecyclerView.Adapter<OnboardingAdapter.ViewHolder>(){

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val image: ImageView= view.findViewById(R.id.image)
        val title: TextView = view.findViewById(R.id.title)
        val desc: TextView= view.findViewById(R.id.desc)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewtype: Int
    ): OnboardingAdapter.ViewHolder {
         val view= LayoutInflater.from(parent.context)
             .inflate(R.layout.item_onboarding,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: OnboardingAdapter.ViewHolder, position: Int) {
        val item=list[position]
        holder.image.setImageResource(item.image)
        holder.title.text=item.title
        holder.desc.text=item.description
    }

    override fun getItemCount(): Int {
        return list.size
    }
}