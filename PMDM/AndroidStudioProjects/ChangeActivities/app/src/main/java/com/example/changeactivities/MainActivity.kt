package com.example.changeactivities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

// definition of requestCode
val RESULT_ONE = 1

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var chgBtn = findViewById<Button>(R.id.changeBtn1)


        chgBtn.setOnClickListener {
            // instantiation of the Intent to go to the second activity
            val intent = Intent(this, MainActivity2::class.java)

            // data to senfd to the second activity
            intent.putExtra("clients", 5)
            intent.putExtra("providers", 50)


            startActivityForResult(intent, RESULT_ONE)
        }
    }

    // funccion called when the second activity sets a result
    @Deprecated("Deprecated a partir de API 30")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val greeting = findViewById<TextView>(R.id.textViewResult)

        // checks correct result
        if (resultCode != Activity.RESULT_OK) return
        when(requestCode) {
            RESULT_ONE -> {
                // if Intent is not null shoew greeting message
                if (data != null) {
                    greeting.text = data.getStringExtra("greeting")
                }
                else {}
            }
        }
    }
}