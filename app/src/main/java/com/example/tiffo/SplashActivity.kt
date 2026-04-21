package com.example.tiffo

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.tiffo.viewmodel.SplashViewModel

class SplashActivity : AppCompatActivity() {
    private lateinit var viewModel: SplashViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val logo=findViewById<ImageView>(R.id.logo)
        val appName= findViewById<TextView>(R.id.appName)
        val tagline= findViewById<TextView>(R.id.tagline)

        viewModel= ViewModelProvider(this)[SplashViewModel::class.java]
        // 🔥 Animation
        logo.animate()
            .alpha(1f)
            .scaleX(1.1f)
            .scaleY(1.1f)
            .setDuration(1000)
            .start()

        appName.animate()
            .alpha(1f)
            .translationY(0f)
            .setDuration(1200)
            .setStartDelay(300)
            .start()

        tagline.animate()
            .alpha(1f)
            .translationY(0f)
            .setDuration(1200)
            .setStartDelay(600)
            .start()

        // 🔥 Start timer
        viewModel.startTimer()

        // 🔥 Observe navigation
        viewModel.navigate.observe(this) {
            if (it) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }
}