package com.example.cameraintent

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val txtView = findViewById<TextView>(R.id.textView)

        // geting the previous Intent
        val intent = intent

        // getting the intent's values
        val n1 = intent.getIntExtra("n1", 0)
        val n2 = intent.getIntExtra("n2", 0)

        txtView.text = "$n1 + $n2"

        var result = n1 + n2

        intent.putExtra("n1", n1)
        intent.putExtra("n2", n2)
        intent.putExtra("result", result)
        setResult(Activity.RESULT_OK, intent)

        finish()
    }
}