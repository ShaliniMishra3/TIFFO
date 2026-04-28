package com.example.tiffo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tiffo.databinding.ActivityMainBinding
import com.example.tiffo.viewmodel.MainViewModel
import com.example.tiffo.adapter.CategoryAdapter
import com.example.tiffo.adapter.FoodAdapter
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.rvCategory.layoutManager = GridLayoutManager(this, 4)
        binding.rvPopular.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvNear.layoutManager = LinearLayoutManager(this)
/*
        binding.bottomBarMain.navProfile.setOnClickListener {
            val intent= Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
        binding.bottomBarMain.navOrders.setOnClickListener {
            val intent= Intent(this, OrderActivity::class.java)
            startActivity(intent)
        }
        binding.bottomBarMain.navCart.setOnClickListener {
            val intent= Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

 */
        selectTab(binding.bottomBarMain.navHome)

        binding.bottomBarMain.navHome.setOnClickListener {

            selectTab(binding.bottomBarMain.navHome)
        }

        binding.bottomBarMain.navProfile.setOnClickListener {
            // already here, just highlight
            val intent = Intent(this, ProfileActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()

            selectTab(binding.bottomBarMain.navProfile)
        }

        binding.bottomBarMain.navOrders.setOnClickListener {
            // open OrdersActivity

            selectTab(binding.bottomBarMain.navOrders)
            val intent = Intent(this, OrderActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()

        }

        binding.bottomBarMain.navCart.setOnClickListener {
            // open CartActivity

            selectTab(binding.bottomBarMain.navCart)
            val intent = Intent(this, CartActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }



        viewModel.categories.observe(this) {
            binding.rvCategory.adapter = CategoryAdapter(it)
        }
        viewModel.popular.observe(this) {
            binding.rvPopular.adapter = FoodAdapter(it, true)
        }
        viewModel.near.observe(this) {
            binding.rvNear.adapter = FoodAdapter(it, false)
        }
    }
    private fun selectTab(selected: View) {

        val tabs = listOf(
            binding.bottomBarMain.navHome,
            binding.bottomBarMain.navProfile,
            binding.bottomBarMain.navOrders,
            binding.bottomBarMain.navCart
        )

        tabs.forEach { tab ->

            val container = tab as ViewGroup   // ✅ FIX

            container.setBackgroundResource(0)

            val icon = container.getChildAt(0) as ImageView
            val text = container.getChildAt(1) as TextView

            icon.setColorFilter(getColor(R.color.white))
            text.setTextColor(getColor(R.color.white))
        }

        val selectedContainer = selected as ViewGroup

        selectedContainer.setBackgroundResource(R.drawable.bg_bottom_selected)

        val icon = selectedContainer.getChildAt(0) as ImageView
        val text = selectedContainer.getChildAt(1) as TextView

        icon.setColorFilter(getColor(R.color.red))
        text.setTextColor(getColor(R.color.red))
    }
}