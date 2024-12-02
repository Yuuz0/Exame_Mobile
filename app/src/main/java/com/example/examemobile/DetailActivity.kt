package com.example.examemobile

import android.os.Build
import android.os.Bundle
import android.service.autofill.UserData
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room

class DetailActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var dataDao: DataDao

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        nameEditText = findViewById(R.id.nameEditText)
        saveButton = findViewById(R.id.saveButton)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "user-database"
        ).build()
        dataDao = db.dataDao()

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val userData = UserData(name = name)
            dataDao.insert(userData)
            Toast.makeText(this, "Data saved!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
