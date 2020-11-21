package com.example.quizpplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.example.quizpplication.BaseCorotineJob.BaseCoroutineJob
import com.example.quizpplication.roomDB.AppDatabase
import com.example.quizpplication.roomDB.Player_Entity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : BaseCoroutineJob() {
    //Create a  variable to hold playerName entered by user
    lateinit var nameEditText: EditText
    private lateinit var db: AppDatabase
     lateinit var playerName:String

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("!!!","Code Runs")
        db = AppDatabase.getInstance(this)

        Log.d("!!!","After db getInstance")

        nameEditText = findViewById(R.id.editTexPersonName)

        startButton.setOnClickListener {
                                                Log.d("!!!","Start Button pressed!")
            playerName = nameEditText.text.toString()
            //Check if value is entered in Text box
            if(playerName != "")
            {
                Log.d("!!!","PlayerName: ${playerName}")
                Log.d("!!!", " calling insert inside Globalscope coroutine function")

                //Coroutine Scope functions
                launch {
                    db.playerDao.deleteAllPlayer()
                }
                launch {
                    val player = Player_Entity(playerName)

                   // db.playerDao.deleteAllPlayer()
                    db.playerDao.insertPlayer(player)

                    Log.d("!!!","After inserting DAO Call")
                    startCategoriesActivity()
                }
            }
            else
            {
                playerName = ""
                Toast.makeText(this,"Please Enter Your Name", Toast.LENGTH_LONG).show()
            }
        }
    }

    //Start Categories Activity
    fun startCategoriesActivity()
    {
        //Create intent to communicate with Categories Activity
        val intent = Intent(this,CategoriesActivity::class.java)
        startActivity(intent)
        finish()
    }
}