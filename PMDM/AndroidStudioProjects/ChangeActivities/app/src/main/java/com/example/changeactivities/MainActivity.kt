package com.example.changeactivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var chgBtn = findViewById<Button>(R.id.changeBtn1)

        chgBtn.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("clients", 5)
            intent.putExtra("providers", 50)
            startActivity(intent)
        }
    }
}