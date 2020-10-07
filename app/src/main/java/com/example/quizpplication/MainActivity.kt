package com.example.quizpplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    //Create a  variable to hold playerName entered by user
    lateinit var nameEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameEditText = findViewById(R.id.editTexPersonName)
        Log.d("!!!","Code Runs")

        startButton.setOnClickListener {
            Log.d("!!!","Start Button pressed!")
            //Check if value is entered in Text box
            if(nameEditText.text.toString() != "")
            {
             /*   Log.d("!!!", "Hej ${nameEditText.text}")
               //Call startQuizQuestionActivity function
                startQuizQuestionActivity()*/

                DataManager.playerName = nameEditText.text.toString()
                Log.d("!!!","PlayerName: ${DataManager.playerName}")

                startCategoriesActivity()

            }
            else
            {
                DataManager.playerName = ""
                Log.d("!!!", "No name entered")
                Log.d("!!!","PLayerName:${DataManager.playerName}")
                Toast.makeText(this,"Please Enter Your Name", Toast.LENGTH_LONG).show()
            }
        }
    }

   /* fun startQuizQuestionActivity()
    {
        //Create intent to pass communicate with QuizQuestion Activity
        val intent = Intent(this,QuizQuestionActivity::class.java)
        //Pass playerName data to QuizQuestion Activity
        intent.putExtra("playerName",nameEditText.text.toString())
        //Start Quiz Question activity
        startActivity(intent)
        //Finish Quiz Question Activity
        finish()
    }*/

    fun startCategoriesActivity()
    {
        val intent = Intent(this,CategoriesActivity::class.java)
        startActivity(intent)
        finish()
    }
}