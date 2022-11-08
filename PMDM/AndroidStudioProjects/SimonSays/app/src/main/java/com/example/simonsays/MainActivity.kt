package com.example.simonsays

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var resultImages: ImageView
    private lateinit var resultText: TextView
    private lateinit var resultBtn: Button

    var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("State", "onCreate")

        setTheme(R.style.Theme_SimonSays)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultImages = findViewById(R.id.white_circle)
        resultImages.setOnClickListener {
            start()
        }
    }

    fun start(){
        showScore()
        resultText = findViewById(R.id.greeting_txt)
        resultText.textSize = 32F
        resultBtn = findViewById(R.id.greenBtn)
        resultBtn.setOnClickListener {
            score++
            showScore()
        }
    }

    fun showScore(){
        resultText = findViewById(R.id.greeting_txt)
        resultText.text = "Score: $score"
    }

    override fun onStart() {
        super.onStart();
        Log.d("State", "onStart")
    }

    override fun onPause() {
        super.onPause();
        Log.d("State", "onPause")
    }

    override fun onRestart() {
        super.onRestart();
        Log.d("State", "onRestart");
    }

    override fun onDestroy() {
        super.onDestroy();
        Log.d("State", "onDestroy")
    }

    override fun onResume() {
        super.onResume();
        Log.d("State", "onResume")
    }

}

