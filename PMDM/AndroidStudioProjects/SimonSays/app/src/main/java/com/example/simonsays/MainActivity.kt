package com.example.simonsays

import android.content.res.ColorStateList
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var resultImages: ImageView
    private lateinit var resultText: TextView
    private var click = true
    private var playing = false

    var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("State", "onCreate")

        setTheme(R.style.Theme_SimonSays)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultImages = findViewById(R.id.white_circle)
        resultImages.setOnClickListener {
            if (!playing) {
                start()
                playing = true
            }
        }
    }

    fun start() {
        Log.d("State", "Starting game")

        val gameSeq = ArrayList<Int>()
        val greenBtn = findViewById<Button>(R.id.greenBtn)
        val redBtn = findViewById<Button>(R.id.redBtn)
        val yellowBtn = findViewById<Button>(R.id.yellowBtn)
        val blueBtn = findViewById<Button>(R.id.blueBtn)
        val colorButtons = listOf(greenBtn, redBtn, yellowBtn, blueBtn)

        resultText = findViewById(R.id.greeting_txt)
        resultText.textSize = 32F
        showScore()
        addStep(gameSeq)
        addStep(gameSeq)
        showSec(gameSeq, colorButtons)


    }

    fun showScore() {
        Log.d("State", "Showing score")

        resultText = findViewById(R.id.greeting_txt)
        resultText.text = "Score: $score"
    }

    fun addStep(seq: MutableList<Int>) {
        Log.d("State", "Adding one step to the sequence")

        val num = (0..3).random()
        seq.add(num)
        Log.d("State", "Add Step")
    }

    fun showSec(seq: MutableList<Int>, colorButtons: List<Button>) {
        Log.d("State", "Showing sequence")

        click = false
        CoroutineScope(Dispatchers.Main).launch {
            seq.forEach {
                delay(350)
                when (it) {
                    0 -> {
                        colorButtons[0].backgroundTintList =
                            ColorStateList.valueOf(resources.getColor(R.color.light_green))
                        delay(500)
                        colorButtons[0].backgroundTintList =
                            ColorStateList.valueOf(resources.getColor(R.color.green))
                    }
                    1 -> {
                        colorButtons[1].backgroundTintList =
                            ColorStateList.valueOf(resources.getColor(R.color.light_red))
                        delay(500)
                        colorButtons[1].backgroundTintList =
                            ColorStateList.valueOf(resources.getColor(R.color.red))
                    }
                    2 -> {
                        colorButtons[2].backgroundTintList =
                            ColorStateList.valueOf(resources.getColor(R.color.light_yelow))
                        delay(500)
                        colorButtons[2].backgroundTintList =
                            ColorStateList.valueOf(resources.getColor(R.color.yellow))
                    }
                    else -> {
                        colorButtons[3].backgroundTintList =
                            ColorStateList.valueOf(resources.getColor(R.color.light_blue))
                        delay(500)
                        colorButtons[3].backgroundTintList =
                            ColorStateList.valueOf(resources.getColor(R.color.blue))
                    }
                }
            }
        }
        click = true
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

