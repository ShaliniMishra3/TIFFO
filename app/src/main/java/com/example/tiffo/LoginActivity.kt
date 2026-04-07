package com.example.tiffo

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.tiffo.adapter.SliderAdapter
import com.google.android.material.button.MaterialButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.hbb20.CountryCodePicker
import java.util.concurrent.TimeUnit

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private var verificationId:String?= null
    private lateinit var viewPager: ViewPager2
    private lateinit var dotsIndicator: LinearLayout
    private lateinit var ccp: CountryCodePicker
    private var currentPhone: String? = null
    private lateinit var dots: Array<ImageView>
    private val locationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {isGranted->
            if (isGranted) {
                Toast.makeText(this, "Location Allowed", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewPager= findViewById(R.id.viewPager)
        dotsIndicator= findViewById(R.id.dotsLayout)
        dotsIndicator = findViewById(R.id.dotsLayout)

        setupSlider()
        setupDots(4) // number of pages

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updateDots(position)
            }
        })
        setupSlider()
        window.decorView.post {
            checkLocationPermission()
        }
       // val ccp = findViewById<CountryCodePicker>(R.id.ccp)
        ccp = findViewById(R.id.ccp)
        val phoneEditText = findViewById<EditText>(R.id.etPhone)
        ccp.registerCarrierNumberEditText(phoneEditText)


        //For Firebase
        auth= FirebaseAuth.getInstance()
        val btnContinue= findViewById<MaterialButton>(R.id.btnContinue)

        btnContinue.setOnClickListener {
            if(ccp.isValidFullNumber){
                val currentPhone= ccp.fullNumberWithPlus
                sendOtp(currentPhone!!)
            }else{
                Toast.makeText(this,"Enter a valid number",Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun updateDots(position: Int) {
        for (i in dots.indices) {
            if (i == position) {
                dots[i].setImageResource(R.drawable.dot_active)
            } else {
                dots[i].setImageResource(R.drawable.dot_inactive)
            }
        }
    }
    private fun setupDots(count: Int) {
        dots = Array(count) { ImageView(this) }
        for (i in 0 until count) {
            dots[i] = ImageView(this)
            dots[i].setImageResource(R.drawable.dot_inactive)
            val params = LinearLayout.LayoutParams(12, 12)
            params.setMargins(6, 0, 6, 0)
            dotsIndicator.addView(dots[i], params)
        }
        dots[0].setImageResource(R.drawable.dot_active)
    }
    private fun setupSlider(){
        val images=listOf(
            R.drawable.e1,
            R.drawable.i3,
            R.drawable.s1,
            R.drawable.d2
        )
        val adapter= SliderAdapter(images)
        viewPager.adapter=adapter
       // TabLayoutMediator(dotsIndicator, viewPager){_, _ ->}.attach()
    }
    private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // Already granted
        } else {
            // 🔥 This shows Zomato-style system popup
            locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }
    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            // 🔥 AUTO OTP DETECT
            val code = credential.smsCode
            val intent = Intent(this@LoginActivity, OTPActivity::class.java)
            intent.putExtra("verificationId", verificationId)
            intent.putExtra("otp", code) // send auto OTP
            // ✅ ADD THIS ALSO
            intent.putExtra("phone", currentPhone)
            startActivity(intent)
        }
        override fun onVerificationFailed(e: FirebaseException) {
            Toast.makeText(this@LoginActivity, e.message, Toast.LENGTH_LONG).show()
        }
        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            super.onCodeSent(verificationId, token)
            this@LoginActivity.verificationId = verificationId
            val intent = Intent(this@LoginActivity, OTPActivity::class.java)
            intent.putExtra("verificationId", verificationId)
            // ✅ ADD THIS LINE
            intent.putExtra("phone", currentPhone)
            startActivity(intent)
        }
    }
    //For OTP
     private fun sendOtp(phone:String) {
         val options = PhoneAuthOptions.newBuilder(auth)
             .setPhoneNumber(phone)
             .setTimeout(60L, TimeUnit.SECONDS)
             .setActivity(this)
             .setCallbacks(callbacks)
             .build()

         PhoneAuthProvider.verifyPhoneNumber(options)
     }
}