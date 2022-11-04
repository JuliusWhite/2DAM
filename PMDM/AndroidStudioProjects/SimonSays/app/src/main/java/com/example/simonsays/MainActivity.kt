package com.example.simonsays

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var resultImages: ImageView
    private lateinit var resultText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultImages = findViewById(R.id.white_circle)
        resultText = findViewById(R.id.greeting_txt)
        resultImages.setOnClickListener {
            resultText.setText(getString(R.string.score))
            resultText.setTextSize(32F)
            resultText.setTextColor((getResources().getColor(R.color.black)))
        }
    }
}

