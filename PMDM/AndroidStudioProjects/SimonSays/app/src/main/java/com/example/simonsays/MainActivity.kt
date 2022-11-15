@file:Suppress("DEPRECATION")

package com.example.simonsays

import android.content.res.ColorStateList
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.*
import java.util.prefs.Preferences
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    private lateinit var resultImages: ImageView
    private lateinit var resultText: TextView
    private lateinit var finalScore: TextView
    private var click = true
    private var playing = false
    private var count = 0
    private var delay = 500L
    private var seqDelay: Long = 700L

    private var score = 0

    private val key = "RECORD"

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

    // Inicialization of variables, showing score, settinh onClickListeners and starting and showing the secuence

    private fun start() {
        Log.d("State", "Starting game")

        val seq = ArrayList<Int>()
        val greenBtn = findViewById<Button>(R.id.greenBtn)
        val redBtn = findViewById<Button>(R.id.redBtn)
        val yellowBtn = findViewById<Button>(R.id.yellowBtn)
        val blueBtn = findViewById<Button>(R.id.blueBtn)
        val colorButtons = listOf(greenBtn, redBtn, yellowBtn, blueBtn)
        seqDelay = 700L

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

    // shows score to the user
    private fun showScore() {
        Log.d("State", "Showing score")

        resultText = findViewById(R.id.greeting_txt)
        resultText.text = "Score: $score"
    }

    // adds one color to the sequence
    private fun addStep(seq: MutableList<Int>) {
        Log.d("State", "Adding one step to the sequence")

        val num = (0..3).random()
        seq.add(num)
    }

    // shows the sequence of colors
    private fun showSec(seq: MutableList<Int>, colorButtons: List<Button>) {
        Log.d("State", "Showing sequence")

        if (score % 3 == 0 && score != 0 && seqDelay >= 350) {
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

    // lights the green button
    private fun lightGreen(colorButtons: List<Button>) {
        CoroutineScope(Dispatchers.Main).launch {
            colorButtons[0].backgroundTintList =
                ColorStateList.valueOf(resources.getColor(R.color.light_green))
            delay(delay)
            colorButtons[0].backgroundTintList =
                ColorStateList.valueOf(resources.getColor(R.color.green))
        }
    }

    // lights the red button
    private fun lightRed(colorButtons: List<Button>) {
        CoroutineScope(Dispatchers.Main).launch {
            colorButtons[1].backgroundTintList =
                ColorStateList.valueOf(resources.getColor(R.color.light_red))
            delay(delay)
            colorButtons[1].backgroundTintList =
                ColorStateList.valueOf(resources.getColor(R.color.red))
        }
    }

    // lights the yellow button
    private fun lightYellow(colorButtons: List<Button>) {
        CoroutineScope(Dispatchers.Main).launch {
            colorButtons[2].backgroundTintList =
                ColorStateList.valueOf(resources.getColor(R.color.light_yelow))
            delay(delay)
            colorButtons[2].backgroundTintList =
                ColorStateList.valueOf(resources.getColor(R.color.yellow))
        }
    }

    // lights the blue button
    private fun lightBlue(colorButtons: List<Button>) {
        CoroutineScope(Dispatchers.Main).launch {
            colorButtons[3].backgroundTintList =
                ColorStateList.valueOf(resources.getColor(R.color.light_blue))
            delay(delay)
            colorButtons[3].backgroundTintList =
                ColorStateList.valueOf(resources.getColor(R.color.blue))
        }
    }

    // checks if the pushed button matches the sequence, if it does the user can continue and the program will add an step
    // if the sequence is correctly introduced, otherwise the sequence stops and show the user the final score, letting them
    // restart a new game
    private fun checkBtn(btnValue: Int, seq: MutableList<Int>, colorButtons: List<Button>) {
        val gameoOverToast = Toast.makeText(applicationContext, "GAME OVER", Toast.LENGTH_SHORT)
        val newRecordToast =
            Toast.makeText(applicationContext, "NEW RECORD: $score!", Toast.LENGTH_SHORT)

        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val record = prefs.getInt(key, 0)
        val editor = prefs.edit()

        if (btnValue != seq[count] && seq.size > 0) {
            gameoOverToast.show()

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

            if (score > record) {
                editor.putInt(key, score)
                editor.apply()
                newRecordToast.show()
            }

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