package com.example.tiffo

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton
import androidx.lifecycle.ViewModelProvider
import com.example.tiffo.adapter.OnboardingAdapter
import com.example.tiffo.viewmodel.OnboardingViewModel

class OnboardingActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var btnNext: MaterialButton

    private lateinit var btnSkip: MaterialButton
    private val viewModel by lazy { ViewModelProvider(this)[OnboardingViewModel::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_onboarding)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewPager = findViewById(R.id.viewPager)
        btnNext = findViewById(R.id.btnNext)
        btnSkip= findViewById(R.id.btnSkip)

        val adapter = OnboardingAdapter(viewModel.items)
        viewPager.adapter = adapter
        // 1. SKIP BUTTON LOGIC
        btnSkip.setOnClickListener {
            navigateToNextPage()
        }

        // 2. NEXT BUTTON LOGIC
        btnNext.setOnClickListener {
            val currentPos = viewPager.currentItem
            val lastPos = viewModel.items.size - 1

            if (currentPos < lastPos) {
                viewPager.currentItem = currentPos + 1
            } else {
                // If it's the very last page
                navigateToNextPage()
            }
        }

        // 3. PAGE CHANGE LISTENER (Premium Touch)
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                // Change button text on the last page to "Get Started"
                if (position == viewModel.items.size - 1) {
                    btnNext.text = "Get Started"
                } else {
                    btnNext.text = "Next"
                }
            }
        })
    }

private fun navigateToNextPage() {
    // Replace 'MainActivity' with your actual next Activity (e.g., RoleSelectionActivity)
    val intent = Intent(this, LoginActivity::class.java)
    startActivity(intent)
   }
}