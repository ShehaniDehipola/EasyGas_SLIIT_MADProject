package com.example.mad_last

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.database.*

class shopOwnerProfile : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_owner_profile)

        // Initialize the database reference
        databaseReference = FirebaseDatabase.getInstance().reference

        // Retrieve the data from the database
        databaseReference.child("shopdetails").child("-NUMR9n6c7mWLZ15p6y6").addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val shopDetails = dataSnapshot.getValue(ShopDetails::class.java)

                    // Display the details in the UI
                    val txtS_Name: TextView = findViewById(R.id.txtS_Name)
                    val txtS_email: TextView = findViewById(R.id.txtS_email)
                    val txtS_address: TextView = findViewById(R.id.txtS_address)
                    val txtS_city: TextView = findViewById(R.id.txtS_city)
                    val txtS_contactno: TextView = findViewById(R.id.txtS_contactno)

                    txtS_Name.text= shopDetails?.name
                    txtS_email.text = shopDetails?.email
                    txtS_address.text = shopDetails?.shopAddress
                    txtS_city.text = shopDetails?.city
                    txtS_contactno.text = shopDetails?.contactNumber
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle the error
            }
        })
    }
}

data class ShopDetails(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val city: String = "",
    val contactNumber: String = "",
    val shopAddress: String = ""
)
