package com.example.diceroller

import android.media.Image
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var roll_text : TextView
    private lateinit var resultImage : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        roll_text = findViewById(R.id.roll_text)
        resultImage = findViewById(R.id.dice_img)

        resultImage.setOnClickListener{
            val animation : Animation = AnimationUtils
                .loadAnimation(applicationContext, R.anim.animation)
            animation.start()

            

        }

    }
}