package com.example.movieposter.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.movieposter.R

class LoginActivity : AppCompatActivity() {

    lateinit var openButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        openButton = findViewById(R.id.login)


        openButton.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}



/*
val MainActivity = findViewById<Button>(R.id.login)
MainActivity.setOnClickListener {
    val Intent = Intent(this, MainActivity::class.java)
    startActivity(intent)*/
