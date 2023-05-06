package com.example.mad_last

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class shopOwnerDashBoard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_owner_dash_board)

        val profileButton = findViewById<ImageButton>(R.id.imgProfile1)
        profileButton.setOnClickListener {
            val intent = Intent(this, shopOwnerProfile::class.java)
            startActivity(intent)
        }
    }
}