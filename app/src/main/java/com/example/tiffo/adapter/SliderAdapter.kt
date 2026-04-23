package com.example.tiffo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tiffo.databinding.ItemSliderBinding

class SliderAdapter(private val images: List<Int>):
      RecyclerView.Adapter<SliderAdapter.SliderViewHolder>(){
          class SliderViewHolder(val binding: ItemSliderBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewtype: Int
    ): SliderViewHolder {
        val binding= ItemSliderBinding.inflate(
           LayoutInflater.from(parent.context),parent,false)
       return SliderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
      holder.binding.image.setImageResource(images[position])
    }
    override fun getItemCount(): Int {
        return images.size
    }
}