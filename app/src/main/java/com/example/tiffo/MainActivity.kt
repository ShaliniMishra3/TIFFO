package com.example.tiffo

import android.os.Bundle
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
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.rvCategory.layoutManager= GridLayoutManager(this,4)
        binding.rvPopular.layoutManager= LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        binding.rvNear.layoutManager= LinearLayoutManager(this)

        viewModel.categories.observe(this){
            binding.rvCategory.adapter= CategoryAdapter(it)
        }
        viewModel.popular.observe(this){
            binding.rvPopular.adapter= FoodAdapter(it,true)
        }
        viewModel.near.observe(this){
            binding.rvNear.adapter= FoodAdapter(it,false)
        }
    }
}