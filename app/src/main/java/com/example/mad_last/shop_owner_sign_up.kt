package com.example.mad_last

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class shop_owner_sign_up : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etShopAddress: EditText
    private lateinit var etShopCity: EditText
    private lateinit var etShopContactNumber: EditText
    private lateinit var etPassword: EditText


    private lateinit var btnSignup: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_owner_sign_up)

        val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        fab.setOnClickListener {
            val intent = Intent(this@shop_owner_sign_up, MainActivity::class.java)
            startActivity(intent)
        }

        etName = findViewById(R.id.username)
        etEmail = findViewById(R.id.email)
        etShopAddress = findViewById(R.id.address)
        etShopCity = findViewById(R.id.city)
        etShopContactNumber = findViewById(R.id.contactnumber)
        etPassword = findViewById(R.id.password)


        btnSignup = findViewById(R.id.btnupdated)

        dbRef = FirebaseDatabase.getInstance().getReference().child("shopOwnerProfile")


        btnSignup.setOnClickListener {
            signupShopOwner()
        }
    }

        private fun signupShopOwner() {

            // getting values
            val name = etName.text.toString()
            val email = etEmail.text.toString()
            val shopAddress = etShopAddress.text.toString()
            val city= etShopCity.text.toString()
            val contactNumber = etShopContactNumber.text.toString()
            val password = etPassword.text.toString()



            // validating inputs
            if (name.isEmpty()) {
                etName.error = "Please enter name"
                etName.requestFocus()
                return
            }
            if (email.isEmpty()) {
                etEmail.error = "Please enter email"
                etEmail.requestFocus()
                return
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.error = "Please enter a valid email"
                etEmail.requestFocus()
                return
            }
            if (password.isEmpty()) {
                etPassword.error = "Please enter password"
                etPassword.requestFocus()
                return
            }
            if (password.length < 6) {
                etPassword.error = "Password should be at least 6 characters"
                etPassword.requestFocus()
                return
            }
            if (shopAddress.isEmpty()) {
                etShopAddress.error = "Please enter shop address"
                etShopAddress.requestFocus()
                return
            }
            if (city.isEmpty()) {
                etShopAddress.error = "Please enter City"
                etShopAddress.requestFocus()
                return
            }
            if (contactNumber.isEmpty()) {
                etShopAddress.error = "Please enter Contact Number"
                etShopAddress.requestFocus()
                return
            }

            // creating a new shop owner object
            val shopOwnerId = dbRef.push().key!!
            val shopOwner = ShopOwnerModel(shopOwnerId, name, email, shopAddress,city,contactNumber,password)

            // saving shop owner data to Firebase Realtime Database
            dbRef.child(shopOwnerId).setValue(shopOwner)
                .addOnCompleteListener {
                    Toast.makeText(this, "Signup successful", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
                .addOnFailureListener { err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                }
        }
    }

