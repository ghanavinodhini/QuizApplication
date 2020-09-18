package com.example.quizpplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var nameEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameEditText = findViewById(R.id.editTexPersonName)
        Log.d("!!!","Code Runs")
        //var name = nameEditText.text.toString()
        startButton.setOnClickListener {
            Log.d("!!!","Start Button pressed!")
            if(nameEditText.text.toString() != "")
            {
                Log.d("!!!", "Hej ${nameEditText.text}")
                startQuizQuestionActivity()

            }
            else
            {
                Log.d("!!!", "No name entered")
                Toast.makeText(this,"Please Enter Your Name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun startQuizQuestionActivity()
    {
        val intent = Intent(this,QuizQuestionActivity::class.java)
        //Start Quiz Question activity
        startActivity(intent)

    }
}