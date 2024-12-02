package com.example.examemobile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class HomeActivity : AppCompatActivity() {
    private lateinit var userRef: DatabaseReference
    private lateinit var userNameTextView: TextView
    private lateinit var addDataButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        userRef = FirebaseDatabase.getInstance().reference.child("users")
        userNameTextView = findViewById(R.id.userNameTextView)
        addDataButton = findViewById(R.id.addDataButton)

        val currentUser = FirebaseAuth.getInstance().currentUser
        currentUser?.let {
            userRef.child(it.uid).get().addOnSuccessListener { snapshot ->
                val userName = snapshot.child("name").value.toString()
                userNameTextView.text = "Welcome, $userName"
            }
        }

        addDataButton.setOnClickListener {
            startActivity(Intent(this, DetailActivity::class.java))
        }
    }
}
