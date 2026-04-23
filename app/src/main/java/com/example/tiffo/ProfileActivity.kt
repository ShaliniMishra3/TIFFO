package com.example.tiffo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tiffo.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.bottomBar.navHome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        binding.btnBack.setOnClickListener {
            finish()
        }
        /*
        val privacy = findViewById<View>(R.id.itemPrivacy)
        privacy.findViewById<TextView>(R.id.txtTitle).text = "Privacy & Settings"
        privacy.findViewById<TextView>(R.id.txtDesc).text = "Make changes to your account"
        //privacy.findViewById<ImageView>(R.id.imgIcon).setImageResource(R.drawable.ic_setting)

        val orders = findViewById<View>(R.id.itemOrders)
        orders.findViewById<TextView>(R.id.txtTitle).text = "Order History"
        orders.findViewById<TextView>(R.id.txtDesc).text = "See your past orders"
       // orders.findViewById<ImageView>(R.id.imgIcon).setImageResource(R.drawable.ic_order)

        orders.findViewById<TextView>(R.id.txtTitle).text = "Payment and Refunds"
        orders.findViewById<TextView>(R.id.txtDesc).text = "manage your billing and payments"

        orders.findViewById<TextView>(R.id.txtTitle).text = "Manage Address"
        orders.findViewById<TextView>(R.id.txtDesc).text = "edit all your registered address"

        orders.findViewById<TextView>(R.id.txtTitle).text = "Help and Support"
        orders.findViewById<TextView>(R.id.txtDesc).text = "Contact us about any issue"

        orders.findViewById<TextView>(R.id.txtTitle).text = "Log Out"
        orders.findViewById<TextView>(R.id.txtDesc).text = "Logout of the app"


         */
        // Privacy
        val privacy = findViewById<View>(R.id.itemPrivacy)
        privacy.findViewById<TextView>(R.id.txtTitle).text = "Privacy & Settings"
        privacy.findViewById<TextView>(R.id.txtDesc).text = "Make changes to your account"

// Orders
        val orders = findViewById<View>(R.id.itemOrders)
        orders.findViewById<TextView>(R.id.txtTitle).text = "Order History"
        orders.findViewById<TextView>(R.id.txtDesc).text = "See your past orders"
        orders.findViewById<ImageView>(R.id.imgIcon).setImageResource(R.drawable.ic_order)


// Payment
        val payment = findViewById<View>(R.id.itemPayment)
        payment.findViewById<TextView>(R.id.txtTitle).text = "Payment & Refunds"
        payment.findViewById<TextView>(R.id.txtDesc).text = "Manage billing and payments"
        payment.findViewById<ImageView>(R.id.imgIcon).setImageResource(R.drawable.ic_payment)

// Address
        val address = findViewById<View>(R.id.itemAddress)
        address.findViewById<TextView>(R.id.txtTitle).text = "Manage Address"
        address.findViewById<TextView>(R.id.txtDesc).text = "Edit your registered addresses"
        address.findViewById<ImageView>(R.id.imgIcon).setImageResource(R.drawable.ic_address)

// Help
        val help = findViewById<View>(R.id.itemHelp)
        help.findViewById<TextView>(R.id.txtTitle).text = "Help & Support"
        help.findViewById<TextView>(R.id.txtDesc).text = "Contact us about any issue"
        help.findViewById<ImageView>(R.id.imgIcon).setImageResource(R.drawable.ic_help)


// Logout
        val logout = findViewById<View>(R.id.itemLogout)
        logout.findViewById<TextView>(R.id.txtTitle).text = "Log Out"
        logout.findViewById<TextView>(R.id.txtDesc).text = "Logout of the app"
        logout.findViewById<ImageView>(R.id.imgIcon).setImageResource(R.drawable.ic_logout)

    }
}