package com.example.tiffo.viewmodel

import androidx.lifecycle.ViewModel
import com.example.tiffo.R
import com.example.tiffo.model.OnboardingItem

class OnboardingViewModel: ViewModel() {
    val items= listOf(
        OnboardingItem(R.drawable.food1,"Home Food","Experience the warmth of home-cooked meals prepared with love by local chefs using the freshest ingredients available"),
        OnboardingItem(R.drawable.delivery,"Fast Delivery", "Skip the cooking and the cleaning. We ensure your tiffin reaches your doorstep piping hot and exactly when you need it."),
        OnboardingItem(R.drawable.meal,"Enjoy Your Food","Savor every bite of tradition. Join our community and rediscover the joy of healthy, wholesome food every single day.")

    )
}