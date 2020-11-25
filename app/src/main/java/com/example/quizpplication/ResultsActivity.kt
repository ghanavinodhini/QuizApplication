package com.example.quizpplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.quizpplication.BaseCorotineJob.BaseCoroutineJob
import com.example.quizpplication.roomDB.AppDatabase
import com.google.android.material.bottomnavigation.BottomNavigationMenu
import kotlinx.android.synthetic.main.activity_results.*
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ResultsActivity : BaseCoroutineJob() {

    lateinit var scoreTextView: TextView
    lateinit var totalQuestionsTextView: TextView
    lateinit var correctAnsweredTextView: TextView
    lateinit var wrongAnsweredTextView: TextView
    lateinit var skippedTextView:TextView
    lateinit var playerNameTextView: TextView
    //Create a variable to hold count for back button press
    var navBackCount = 0
    private lateinit var db: AppDatabase
    lateinit var playerName:String

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        Log.d("!!!","Inside Results Activity")
        //Get instance of Database
        db = AppDatabase.getInstance(this)
        Log.d("!!!","After getting DB instance")

        runBlocking {
            launch {
                Log.d("!!!", "Inside launch scope")
                //delay(1000)
                playerName = db.playerDao.getPlayer()
            }
        }

                                        //Log.d("!!!","In Results Activity")
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
       // val playerName = DataManager.playerName
                                        //Log.d("!!!","Score value: $score")
        //Pass values to strings.xml file
        scoreTextView.text = getString(R.string.score_textview,score.toString())
        totalQuestionsTextView.text = getString(R.string.totalQuestions_textview,totalQuestions.toString())
        correctAnsweredTextView.text = getString(R.string.correctlyAnswered_textview,correctAnswers.toString())
        wrongAnsweredTextView.text = getString(R.string.wronglyAnswered_textview,wrongAnswers.toString())
        skippedTextView.text = getString(R.string.skipped_textview,skipQuestions.toString())
        playerNameTextView.text = getString(R.string.playername_textview,playerName)

        displayTrophyImage(score,totalQuestions)

    //Bottom Navigation menu
        bottomNavigationResults.setOnNavigationItemSelectedListener {
        when(it.itemId)
        {
        R.id.action_home->restartQuiz()
        R.id.action_exit->displayAlert()
        }
        true
    }
    }

    //Display trophy image based on score
    private fun displayTrophyImage(score:Int,totalQuestions:Int)
    {
    when(score)
    {
        totalQuestions->trophyImageView.setImageResource(R.drawable.trophy)
        0-> trophyImageView.setImageResource(R.drawable.lose_emoji)
        else->trophyImageView.setImageResource(R.drawable.star_win_emoji)
    }

    }
    //Restart MainActivity on Replay
    private fun restartQuiz()
    {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    //Display Alert Dialog for quit app
    private fun displayAlert()
    {
        val dialogBuilder = AlertDialog.Builder(this,R.style.AlertDialogTheme)
        dialogBuilder.setTitle("Confirm Exit!!")
                    .setMessage("Are You Sure To Quit?")
                    .setPositiveButton("YES")
        {
                dialog,which->finish()
        }
        dialogBuilder.setNegativeButton("NO")
        {
            dialog,which->
            Toast.makeText(this,"You clicked NO button",Toast.LENGTH_SHORT).show()
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
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show()
        }
        if (navBackCount == 2)
        {
            super.onBackPressed()
            return
        }
    }
}