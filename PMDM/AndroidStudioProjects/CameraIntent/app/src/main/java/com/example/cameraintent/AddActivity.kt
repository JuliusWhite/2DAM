package com.example.cameraintent

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        // instantiation of graphical elements
        val txtView = findViewById<TextView>(R.id.numbersView)
        val editTxt = findViewById<EditText>(R.id.editTextAnswer)
        val checkButton = findViewById<Button>(R.id.checkButton)

        // geting the previous Intent
        val intent = intent

        // getting the intent's values
        val n1 = intent.getIntExtra("n1", 0)
        val n2 = intent.getIntExtra("n2", 0)

        // showing numbers
        txtView.text = "$n1+$n2=?"

        // giving values to variables
        var result = n1 + n2
        Log.d("State", "Result: $result")

        checkButton.setOnClickListener {
            var aux = editTxt.text
            var answer = Integer.parseInt(editTxt.text.toString())
            Log.d("State", "Result: $result, answer: $answer")
            var toret = "Error"
            if (result == answer) {
                toret = "Correct answer!"
            }
            else {
                toret = "Incorrect answer!"
            }
            intent.putExtra("answer", toret)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}