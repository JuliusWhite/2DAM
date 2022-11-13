package com.example.simonsays

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var resultImages: ImageView
    private lateinit var resultText: TextView
    private lateinit var finalScore: TextView
    private var click = true
    private var playing = false
    private var count = 0
    private var delay = 500L
    private var seqDelay = 700L

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

        val seq = ArrayList<Int>()
        val greenBtn = findViewById<Button>(R.id.greenBtn)
        val redBtn = findViewById<Button>(R.id.redBtn)
        val yellowBtn = findViewById<Button>(R.id.yellowBtn)
        val blueBtn = findViewById<Button>(R.id.blueBtn)
        val colorButtons = listOf(greenBtn, redBtn, yellowBtn, blueBtn)

        finalScore = findViewById(R.id.finalScore)
        finalScore.visibility = View.INVISIBLE
        resultText = findViewById(R.id.greeting_txt)
        resultText.textSize = 32F
        showScore()
        addStep(seq)
        showSec(seq, colorButtons)

        greenBtn.setOnClickListener {
            if (click) {
                checkBtn(0, seq, colorButtons)
                lightGreen(colorButtons)
            }
        }

        redBtn.setOnClickListener {
            if (click) {
                checkBtn(1, seq, colorButtons)
                lightRed(colorButtons)
            }
        }

        yellowBtn.setOnClickListener {
            if (click) {
                checkBtn(2, seq, colorButtons)
                lightYellow(colorButtons)
            }
        }

        blueBtn.setOnClickListener {
            if (click) {
                checkBtn(3, seq, colorButtons)
                lightBlue(colorButtons)
            }
        }
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
    }

    fun showSec(seq: MutableList<Int>, colorButtons: List<Button>) {
        Log.d("State", "Showing sequence")

        if (score % 3 == 0 && score != 0 && seqDelay >= 350){
            seqDelay -= 50L
        }
        CoroutineScope(Dispatchers.Main).launch {
            click = false
            seq.forEach {
                delay(seqDelay)
                when (it) {
                    0 -> lightGreen(colorButtons)
                    1 -> lightRed(colorButtons)
                    2 -> lightYellow(colorButtons)
                    else -> lightBlue(colorButtons)
                }
            }
            click = true
        }
    }

    fun lightGreen(colorButtons: List<Button>) {
        CoroutineScope(Dispatchers.Main).launch {
            colorButtons[0].backgroundTintList =
                ColorStateList.valueOf(resources.getColor(R.color.light_green))
            delay(delay)
            colorButtons[0].backgroundTintList =
                ColorStateList.valueOf(resources.getColor(R.color.green))
        }
    }

    fun lightRed(colorButtons: List<Button>) {
        CoroutineScope(Dispatchers.Main).launch {
            colorButtons[1].backgroundTintList =
                ColorStateList.valueOf(resources.getColor(R.color.light_red))
            delay(delay)
            colorButtons[1].backgroundTintList =
                ColorStateList.valueOf(resources.getColor(R.color.red))
        }
    }

    fun lightYellow(colorButtons: List<Button>) {
        CoroutineScope(Dispatchers.Main).launch {
            colorButtons[2].backgroundTintList =
                ColorStateList.valueOf(resources.getColor(R.color.light_yelow))
            delay(delay)
            colorButtons[2].backgroundTintList =
                ColorStateList.valueOf(resources.getColor(R.color.yellow))
        }
    }

    fun lightBlue(colorButtons: List<Button>) {
        CoroutineScope(Dispatchers.Main).launch {
            colorButtons[3].backgroundTintList =
                ColorStateList.valueOf(resources.getColor(R.color.light_blue))
            delay(delay)
            colorButtons[3].backgroundTintList =
                ColorStateList.valueOf(resources.getColor(R.color.blue))
        }
    }

    fun checkBtn(btnValue: Int, seq: MutableList<Int>, colorButtons: List<Button>) {
        if (btnValue != seq[count] && seq.size > 0) {
            val toast = Toast.makeText(applicationContext, "GAME OVER", Toast.LENGTH_SHORT)
            toast.show()
            click = false
            delay = 800L
            lightGreen(colorButtons)
            lightRed(colorButtons)
            lightYellow(colorButtons)
            lightBlue(colorButtons)
            delay = 500L
            resultText = findViewById(R.id.greeting_txt)
            resultText.textSize = 40F
            resultText.text = "RESTART"
            finalScore = findViewById(R.id.finalScore)
            finalScore.text = "Final score: $score"
            finalScore.visibility = View.VISIBLE
            score = 0
            count = 0
            playing = false
        } else {
            count++
            if (count == seq.size) {
                score++
                showScore()
                count = 0
                addStep(seq)
                showSec(seq, colorButtons)
            }
        }
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