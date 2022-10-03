package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var roll_text: TextView
    private lateinit var resultImages: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        roll_text = findViewById(R.id.roll_text)
        resultImages = findViewById(R.id.dice_img)

        resultImages.setOnClickListener {
            val animation: Animation =
                AnimationUtils.loadAnimation(applicationContext, R.anim.animation)
            animation.start()
            rollDice()
        }
    }

    private fun rollDice() {
        val randomInt = (1..6).random()
        val drawableImage = when (randomInt) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        val tvText = randomInt.toString()
        resultImages.setImageResource(drawableImage)
        roll_text.text = "Dice Roll is $tvText"
        Toast.makeText(this, "Dice Roll is $tvText", Toast.LENGTH_SHORT).show()
    }
}