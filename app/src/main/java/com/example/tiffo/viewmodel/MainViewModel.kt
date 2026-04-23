package com.example.tiffo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tiffo.R
import com.example.tiffo.model.Category
import com.example.tiffo.model.FoodItem

class MainViewModel: ViewModel() {
    val categories= MutableLiveData(
        listOf(
            Category("Breakfast", R.drawable.ic_veg),
            Category("Launch", R.drawable.ic_launch),
            Category("Snack", R.drawable.ic_snack),
            Category("Dinner", R.drawable.ic_dinner)        )
    )
    val popular = MutableLiveData(sample())
    val near = MutableLiveData(sample())
    private fun sample() = listOf(
        FoodItem("Keto Delight", "Boiled Chicken, Nuts, Veggie Salad", 4.6),
        FoodItem("Sugar Rush","Gulab jamun, Kheer, Barfi",4.5),
        FoodItem("Delish Kitchen","North indian meals made using home-style recipes.",4.2))
}