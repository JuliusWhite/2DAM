package com.example.changeactivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        var chgBtn2 = findViewById<Button>(R.id.changeBtn2)

        chgBtn2.setOnClickListener {
            val intent2 = Intent(this, MainActivity::class.java)
            startActivity(intent2)
        }

        val intent = intent

        val clientsValue = intent.getIntExtra("clients", 0)
        val providersValue = intent.getIntExtra("providers", 0)
        val numClients = findViewById<TextView>(R.id.txtViewClients)
        val numProviders = findViewById<TextView>(R.id.txtViewProviders)
        numClients.text = "Clientes: $clientsValue"
        numProviders.text = "Providers: $providersValue"
    }
}