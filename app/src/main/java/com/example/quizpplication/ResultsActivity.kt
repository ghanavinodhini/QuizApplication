package com.example.quizpplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_results.*

class ResultsActivity : AppCompatActivity() {

    lateinit var scoreTextView: TextView
    lateinit var totalQuestionsTextView: TextView
    lateinit var correctAnsweredTextView: TextView
    lateinit var wrongAnsweredTextView: TextView
    lateinit var skippedTextView:TextView
    lateinit var playerNameTextView: TextView
    //Create a variable to hold count for back button press
    var navBackCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        Log.d("!!!","In Results Activity")
        scoreTextView = findViewById(R.id.score_textView)
        totalQuestionsTextView = findViewById(R.id.totalQuestions_textView)
        correctAnsweredTextView = findViewById(R.id.correctlyAnswered_textView)
        wrongAnsweredTextView = findViewById(R.id.wronglyAnswered_textView)
        skippedTextView = findViewById(R.id.skipped_textView)
        playerNameTextView = findViewById(R.id.playerName_textView)

        val score = getIntent().getIntExtra("scoreValue",0)
        val correctAnswers = getIntent().getIntExtra("correctAnswered",0)
        val wrongAnswers = getIntent().getIntExtra("wrongAnswered",0)
        val skipQuestions = getIntent().getIntExtra("skipped",0)
        val totalQuestions = getIntent().getIntExtra("noOfQuestions",0)
        val playerName = DataManager.playerName

        Log.d("!!!","Score value: $score")


        //Pass values to strings.xml file
        scoreTextView.text = getString(R.string.score_textview,score.toString())
        totalQuestionsTextView.text = getString(R.string.totalQuestions_textview,totalQuestions.toString())
        correctAnsweredTextView.text = getString(R.string.correctlyAnswered_textview,correctAnswers.toString())
        wrongAnsweredTextView.text = getString(R.string.wronglyAnswered_textview,wrongAnswers.toString())
        skippedTextView.text = getString(R.string.skipped_textview,skipQuestions.toString())
       playerNameTextView.text = getString(R.string.playername_textview,playerName)


        replay_button.setOnClickListener{
            restartQuiz()
        }

        quitButton.setOnClickListener{
            displayAlert()
        }
    }
    //Restart MainActivity on Replay
    fun restartQuiz()
    {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    //Display Alert Dialog for quit app
    fun displayAlert()
    {
        val dialogBuilder = AlertDialog.Builder(this,R.style.AlertDialogTheme)
        dialogBuilder.setTitle("Exit")
                    .setMessage("Are You Sure To Quit?")
                    .setPositiveButton("YES")
        {
                dialog,which->finish()
        }
        dialogBuilder.setNegativeButton("NO")
        {
            dialog,which->
            Toast.makeText(this,"You clicked NO button",Toast.LENGTH_LONG).show()
        }

        val alert = dialogBuilder.create()
        alert.show()
    }

    //Back navigation button handling
    override fun onBackPressed()
    {
        navBackCount++

        if (navBackCount == 1)
        {
            Log.d("!!!", "pressed once")
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_LONG).show()
        }
        if (navBackCount == 2)
        {
            Log.d("!!!", "Back button pressed twice")
            super.onBackPressed()
            return
        }

    }

}