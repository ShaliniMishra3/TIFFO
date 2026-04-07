package com.example.tiffo

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class OTPActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private var verificationId:String?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_otpactivity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnBack=findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener {
            val intent= Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        val phone = intent.getStringExtra("phone")
        Toast.makeText(this, "OTP sent to $phone", Toast.LENGTH_SHORT).show()
        setupOtpInputs()
        auth = FirebaseAuth.getInstance()
        verificationId = intent.getStringExtra("verificationId")
        val autoOtp = intent.getStringExtra("otp")
        if (autoOtp != null && autoOtp.length == 6) {
            verifyOtp(autoOtp)
        }
        if (phone != null) {
            Toast.makeText(this, "OTP sent to $phone", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "OTP sent", Toast.LENGTH_SHORT).show()
        }
        val otp1 = findViewById<EditText>(R.id.otp1)
        val otp2 = findViewById<EditText>(R.id.otp2)
        val otp3 = findViewById<EditText>(R.id.otp3)
        val otp4 = findViewById<EditText>(R.id.otp4)
        val otp5 = findViewById<EditText>(R.id.otp5)
        val otp6 = findViewById<EditText>(R.id.otp6)
        val otp =
            otp1.text.toString() +
                    otp2.text.toString() +
                    otp3.text.toString() +
                    otp4.text.toString() +
                    otp5.text.toString() +
                    otp6.text.toString()
        val btnVerify = findViewById<MaterialButton>(R.id.btnVerify)
        btnVerify.setOnClickListener {
            val otp =
                otp1.text.toString() +
                        otp2.text.toString() +
                        otp3.text.toString() +
                        otp4.text.toString() +
                        otp5.text.toString() +
                        otp6.text.toString()

            if (otp.length == 6) {
                verifyOtp(otp)
            } else {
                Toast.makeText(this, "Enter valid OTP", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun verifyOtp(code: String) {

        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)

        signInWithCredential(credential)
    }

    private fun signInWithCredential(credential: PhoneAuthCredential) {

        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->

                if (task.isSuccessful) {

                    Toast.makeText(this, "Login Success 🎉", Toast.LENGTH_SHORT).show()

                    startActivity(Intent(this, NotificationActivity::class.java))
                    finish()

                } else {
                    Toast.makeText(this, "Invalid OTP ❌", Toast.LENGTH_SHORT).show()
                }
            }
    }
    private fun setupOtpInputs() {
        val otp1 = findViewById<EditText>(R.id.otp1)
        val otp2 = findViewById<EditText>(R.id.otp2)
        val otp3 = findViewById<EditText>(R.id.otp3)
        val otp4 = findViewById<EditText>(R.id.otp4)
        val otp5 = findViewById<EditText>(R.id.otp5)
        val otp6 = findViewById<EditText>(R.id.otp6)

        otp1.addTextChangedListener(SimpleTextWatcher { otp2.requestFocus() })
        otp2.addTextChangedListener(SimpleTextWatcher { otp3.requestFocus() })
        otp3.addTextChangedListener(SimpleTextWatcher { otp4.requestFocus() })
        otp4.addTextChangedListener(SimpleTextWatcher { otp5.requestFocus() })
        otp5.addTextChangedListener(SimpleTextWatcher { otp6.requestFocus() })
    }
}