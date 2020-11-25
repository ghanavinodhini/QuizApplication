package com.example.quizpplication

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.quizpplication.BaseCorotineJob.BaseCoroutineJob
import com.example.quizpplication.roomDB.AppDatabase
import com.example.quizpplication.roomDB.Question_Entity
import kotlinx.android.synthetic.main.activity_quiz_question.*
import kotlinx.coroutines.*


/*class QuizQuestionActivity : AppCompatActivity(),View.OnClickListener
{
    //Each time this Activity starts first time, we start with question 1
    private var myCurrentQuestion:Int = 1
    //Create instance of class Question as List variable to hold all questions details
    private var myQuestionsList= mutableListOf<Question>()
    //Set variable to hold correctly answered
    private var correctAnswered = 0
    //Set variable to hold wrongly answered
    private var wrongAnswered = 0
    //Set variable to hold skipped answers
    private var skippedQuestions = 0
    //Set score value
    private var score = 0
    //Set flag variable to deactivate buttons after click
    private var optionButtonClickFlag = false
    //Create timer variable
    private lateinit var timer:CountDownTimer
    //Create variable to hold categorytext view value
    private lateinit var categoryName:String

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        //Retreive category textview value
        categoryName = getIntent().getStringExtra("categoryname").toString()
                                    //Log.d("!!!", "Category value: $categoryName")

        //Set Question textview
        val questionTextView = findViewById<TextView>(R.id.questionTextView)
        //Create instance of class Question to access its members and functions
        val qDisplay = Question()
        // Retreive list of questions
        questionTextView.text = getString(R.string.question_textview, categoryName.dropLast(1))
        myQuestionsList = qDisplay.getQuestions(categoryName)

                                    //Log.d("!!!", "No.of questions: ${myQuestionsList.size}")
        //Set first question
        setQuestion()

        //Timer Implementation
         timer = MyCounter(10 * 1000, 1000)
         timer.start()


        optionOneButton.setOnClickListener(this)
        optionTwoButton.setOnClickListener(this)
        optionThreeButton.setOnClickListener(this)
        optionFourButton.setOnClickListener(this)

        //Implement next button click functionalities
        next_floatButton.setOnClickListener{
                                    //Log.d("!!!", "next button pressed!!")
                timer.start()
                optionButtonClickFlag = false
                myCurrentQuestion++
                displayNextQuestion(myCurrentQuestion)
            }
        //Start Results Activity on doneAll button click
        doneAll_floatButton.setOnClickListener{
                                    //Log.d("!!!", "Done All button pressed!")
            startResultsActivity()
        }
    }

    //Implement Timer countdown
    inner class MyCounter(millisInFuture: Long, countDownInterval: Long):CountDownTimer(millisInFuture, countDownInterval)
    {
        var timerCount = 0
        override fun onTick(millisUntilFinished: Long) {
            timerCount = (millisUntilFinished/1000).toInt()+1
            timerTextView.text = timerCount.toString()
        }
    //Implement timer OnFinish()
        override fun onFinish() {
            displayTimeUpMessage("TIMEOUT", 500)
            timerCount = 0
            timerTextView.text = timerCount.toString()

            //Check if current Question is not final question and display next button
            if(myCurrentQuestion!=myQuestionsList.size)
                next_floatButton.show()
            else
                doneAll_floatButton.show()
            //Set option button click to true
            optionButtonClickFlag = true
            //Disable all option buttons
            defaultOptionsView()
            //Display correct Answer when timer finishes
            answerDisplay(myQuestionsList.get(myCurrentQuestion - 1).correctAnswer)
            //Display original image for current question
            myQuestionsList.get(myCurrentQuestion - 1).image?.let { qv_imageView.setImageResource(it) }
            //Increment skipped Questions
            skippedQuestions+=1
                                        //Log.d("!!!", "Skipped: $skippedQuestions")
        }
    }
    //Display Toast Timeup message for half second
    private fun displayTimeUpMessage(text: String, duration: Long)
    {
            val toast = Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT)
            toast.show()
            val handler = Handler()
            handler.postDelayed(object : Runnable {
                override fun run() {
                    toast.cancel()
                }
            }, duration)
    }

    //Diplay next question if question number lessthan/equalto questionsList
    private fun displayNextQuestion(questionNumber: Int)
    {
        if(questionNumber <= myQuestionsList.size){
            setQuestion()
        }
        else{
            Log.d("!!!", "Quiz completed!!")
        }
    }

    //Display Questions Details
    private fun setQuestion()
    {
        //Get current Question details from questionsList
        val currentQuestionDisplay = myQuestionsList.get(myCurrentQuestion - 1)
        //Set default border for option buttons
        defaultOptionsView()
        //Hide next button
        next_floatButton.hide()
        //Hide doneAll Button
        doneAll_floatButton.hide()

        //Set progress Bar progress to currentQuestion value
        progressBar.progress = myCurrentQuestion
        progressTextView.text = "$myCurrentQuestion" + "/" + progressBar.max


        //Display grayscale image for current question
        currentQuestionDisplay.image_grayscale?.let { qv_imageView.setImageResource(it) }

        //Display text in all option buttons
        optionOneButton.text = currentQuestionDisplay.optionOne
        optionTwoButton.text = currentQuestionDisplay.optionTwo
        optionThreeButton.text = currentQuestionDisplay.optionThree
        optionFourButton.text = currentQuestionDisplay.optionFour
    }

    //Implement choices button display
    private fun defaultOptionsView()
    {
        //Create list variable for buttons and add all buttons
        val options = mutableListOf<Button>()
        options.add(0, optionOneButton)
        options.add(1, optionTwoButton)
        options.add(2, optionThreeButton)
        options.add(3, optionFourButton)

        for(opt in options)
        {
            //To set option border by default for unselected button choices
            opt.background = ContextCompat.getDrawable(this, R.drawable.option_border_bg)
            //Enable and Disable buttons based on flag value
            if(!optionButtonClickFlag)
                opt.isEnabled = true
            else opt.isEnabled = false
        }
    }

    //Implement option buttons onClick functionality
    override fun onClick(view: View?)
    {

        //Cancel timer on option button click
        timer.cancel()
        //Set button click flag to true on Click
        optionButtonClickFlag = true
        //Check if current Question is not last question and display next button
        if(myCurrentQuestion!=myQuestionsList.size)
        next_floatButton.show()
        else
            //Display doneAll button in last question
            doneAll_floatButton.show()
        //Validate each option button on button click
        when(view?.id)
        {
            R.id.optionOneButton -> {
                selectedOptionValidate(optionOneButton, 1)
            }
            R.id.optionTwoButton -> {
                selectedOptionValidate(optionTwoButton, 2)
            }
            R.id.optionThreeButton -> {
                selectedOptionValidate(optionThreeButton, 3)
            }
            R.id.optionFourButton -> {
                selectedOptionValidate(optionFourButton, 4)
            }
        }
    }


    //Validate user selected option
    private fun selectedOptionValidate(selectedBtn: Button, selectedOptionNumber: Int)
    {
        val currentQuestionValidate = myQuestionsList!!.get(myCurrentQuestion - 1)
        //Set to original image
        currentQuestionValidate.image?.let { qv_imageView.setImageResource(it) }
        //Set all other options default
        defaultOptionsView()


        //Compare user selected option and correct answer and display button in Green color if correct
        if(selectedOptionNumber==currentQuestionValidate.correctAnswer)
        {
            selectedBtn.background = ContextCompat.getDrawable(this, R.drawable.correct_button_color)
                correctAnswered += 1
                score += 1
        }
        else {
            //Display button in Red Color if user selected option is wrong
            selectedBtn.background = ContextCompat.getDrawable(this, R.drawable.wrong_buton_color)
                wrongAnswered+=1
            answerDisplay(currentQuestionValidate.correctAnswer)
        }
    }

    //Display correct answer
    private fun answerDisplay(answer: Int)
    {
                                    //Log.d("!!!", "Inside answerDisplay function")
        when(answer)
        {
            1 -> {
                optionOneButton.background = ContextCompat.getDrawable(this, R.drawable.correct_button_color)
                manageButtonAnimation(optionOneButton)
            }

            2 -> {
                optionTwoButton.background = ContextCompat.getDrawable(this, R.drawable.correct_button_color)
                manageButtonAnimation(optionTwoButton)
            }

            3 -> {
                optionThreeButton.background = ContextCompat.getDrawable(this, R.drawable.correct_button_color)
                manageButtonAnimation(optionThreeButton)
            }

            4 -> {
                optionFourButton.background = ContextCompat.getDrawable(this, R.drawable.correct_button_color)
                manageButtonAnimation(optionFourButton)
            }
        }
    }

    private fun startResultsActivity()
    {
        val intent = Intent(this, ResultsActivity::class.java)

        //Pass data to Results Activity
        intent.putExtra("scoreValue", score)
        intent.putExtra("noOfQuestions", myQuestionsList.size)
        intent.putExtra("correctAnswered", correctAnswered)
        intent.putExtra("wrongAnswered", wrongAnswered)
        intent.putExtra("skipped", skippedQuestions)

        //Start Results activity
        startActivity(intent)
        //Finish Question Activity
        finish()
    }

    //Back navigation button handling to Categories Activity
    override fun onBackPressed()
    {
        timer.cancel()
        val intent = Intent(this, CategoriesActivity::class.java)
        startActivity(intent)
        //Finish Question Activity
        finish()
    }

    private fun manageButtonAnimation(btn:Button)
    {
        val btnAnimation = AnimationUtils.loadAnimation(this, R.anim.blink)
        btn.startAnimation(btnAnimation)
    }
}*/

class QuizQuestionActivity : BaseCoroutineJob(),View.OnClickListener
{
    //Create variable to hold categorytext view value
    private lateinit var categoryName:String
    private lateinit var db: AppDatabase
    private lateinit var qList:MutableList<Question_Entity>
    private lateinit var myQListByCategory : List<Question_Entity>
    //Each time this Activity starts first time, we start with question 1
    private var myCurrentQuestion:Int = 1
    //Set flag variable to deactivate buttons after click
    private var optionButtonClickFlag = false
    private lateinit var questionTextView:TextView
    //Create timer variable
    private lateinit var timer:CountDownTimer
    //Set variable to hold correctly answered
    private var correctAnswered = 0
    //Set variable to hold wrongly answered
    private var wrongAnswered = 0
    //Set variable to hold skipped answers
    private var skippedQuestions = 0
    //Set score value
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        Log.d("!!!","Inside Quiz Question Activity")
        //Retreive category textview value
        categoryName = getIntent().getStringExtra("categoryname").toString()
        Log.d("!!!", "Category value: $categoryName")

        //Set Question textview
         questionTextView = findViewById<TextView>(R.id.questionTextView)

        //Get instance of Database
        db = AppDatabase.getInstance(this)

        //Insert Question Data
        Log.d("!!!","Before calling Datamanger.InsertQuestion function inside launch scope")

        qList = DataManager.allQuestionsList
        Log.d("!!!","qList Size: ${qList.size}")
        Log.d("!!!","QList value : $qList")

        //Coroutine Scope functions
        runBlocking {

        launch {
            Log.d("!!!","Current Thread:${Thread.currentThread().name}")
            db.questionDao.deleteAllQuestions()
            Log.d("!!!","After deleteALLQUESTIONS")
        }

        launch{
            Log.d("!!!","Current Thread:${Thread.currentThread().name}")
            for(i in 0..qList.size-1) {
                //delay(1000)
                db.questionDao.insertQuestion(qList[i])
                Log.d("!!!", "After insertQuestions DAO Call")
            }
        }


        //Get Questions by Category from database table
       // Log.d("!!!","Before calling async coroutine for getting questions from table")
      // val qListDeferred = async{
            launch {
                delay(1500)
                Log.d("!!!","Current Thread:${Thread.currentThread().name}")
                myQListByCategory = db.questionDao.getQuestionsByCategory(categoryName)
                Log.d("!!!","Questions List by Category Size: ${myQListByCategory.size}")
            }


        launch {
            Log.d("!!!", "Before calling setQuestion() inside launch")
            Log.d("!!!", "Current Thread:${Thread.currentThread().name}")
            //val listByCat = listOf(qListDeferred.await())
            delay(1700)
            if(myQListByCategory!=null)
            setQuestion()
            //next_floatButton.show()
        }
        }
        //Set first question
        Log.d("!!!","After calling setQuestion()")
        //setQuestion()

        //Timer Implementation
        timer = MyCounter(10 * 1000, 1000)
        timer.start()

        //Implement next button click functionalities
        next_floatButton.setOnClickListener{
            Log.d("!!!", "next button pressed!!")
            timer.start()
            optionButtonClickFlag = false
            myCurrentQuestion++
            displayNextQuestion(myCurrentQuestion)
        }

        //Start Results Activity on doneAll button click
        doneAll_floatButton.setOnClickListener{
            //Log.d("!!!", "Done All button pressed!")
            startResultsActivity()
        }

        optionOneButton.setOnClickListener(this)
        optionTwoButton.setOnClickListener(this)
        optionThreeButton.setOnClickListener(this)
        optionFourButton.setOnClickListener(this)

    }

    //Display Questions Details
      fun setQuestion() {
        Log.d("!!!","Inside setQuestion()")
        //Get current Question details from questionsList
        val currentQuestionDisplay = myQListByCategory.get(myCurrentQuestion - 1)
        Log.d("!!!","Current Question Display value: $currentQuestionDisplay")
        //Set default border for option buttons
        defaultOptionsView()
        //Hide next button
        next_floatButton.hide()
        //Hide doneAll Button
        doneAll_floatButton.hide()

        //Set progress Bar progress to currentQuestion value
        progressBar.progress = myCurrentQuestion
        progressTextView.text = "$myCurrentQuestion" + "/" + progressBar.max
        //Display question from table in textview
        questionTextView.text = currentQuestionDisplay.question
        Log.d("!!!","Questiontextview: ${questionTextView.text}")

        //Display grayscale image for current question
        //currentQuestionDisplay.image_grayscale?.let { qv_imageView.setImageResource(it) }

        val imgSource = currentQuestionDisplay.imgName.toLowerCase() + "image_grayscale"
        Log.d("!!!","Image Sorce Grayscale: imgSource")
        val resID = resources.getIdentifier(imgSource,"drawable",packageName)
        qv_imageView.setImageResource(resID)

        //Display text in all option buttons
        optionOneButton.text = currentQuestionDisplay.optionOne
        optionTwoButton.text = currentQuestionDisplay.optionTwo
        optionThreeButton.text = currentQuestionDisplay.optionThree
        optionFourButton.text = currentQuestionDisplay.optionFour
        Log.d("!!!","After displaying text in option buttons")

    }

    //Implement choices button display
    private fun defaultOptionsView()
    {
        Log.d("!!!","Inside default options view function")
        //Create list variable for buttons and add all buttons
        val options = mutableListOf<Button>()
        options.add(0, optionOneButton)
        options.add(1, optionTwoButton)
        options.add(2, optionThreeButton)
        options.add(3, optionFourButton)

        for(opt in options)
        {
            //To set option border by default for unselected button choices
            opt.background = ContextCompat.getDrawable(this, R.drawable.option_border_bg)
            //Enable and Disable buttons based on flag value
            if(!optionButtonClickFlag)
                opt.isEnabled = true
            else opt.isEnabled = false
        }
    }

    //Implement Timer countdown
    inner class MyCounter(millisInFuture: Long, countDownInterval: Long):CountDownTimer(millisInFuture, countDownInterval)
    {
        var timerCount = 0
        override fun onTick(millisUntilFinished: Long) {
            timerCount = (millisUntilFinished/1000).toInt()+1
            timerTextView.text = timerCount.toString()
        }
        //Implement timer OnFinish()
        override fun onFinish() {
            displayTimeUpMessage("TIMEOUT", 500)
            timerCount = 0
            timerTextView.text = timerCount.toString()

            //Check if current Question is not final question and display next button
            if(myCurrentQuestion!=myQListByCategory.size)
                next_floatButton.show()
            else
                doneAll_floatButton.show()
            //Set option button click to true
            optionButtonClickFlag = true
            //Disable all option buttons
            defaultOptionsView()
            //Display correct Answer when timer finishes
            answerDisplay(myQListByCategory.get(myCurrentQuestion - 1).correctAnswer)
            //Display original image for current question
           // myQListByCategory.get(myCurrentQuestion - 1).image?.let { qv_imageView.setImageResource(it) }
            val imgSource = myQListByCategory.get(myCurrentQuestion-1).imgName.toLowerCase() + "image"
            Log.d("!!!","Original Image Source: $imgSource")
            val resID = resources.getIdentifier(imgSource,"drawable",packageName)
            qv_imageView.setImageResource(resID)

            //Increment skipped Questions
            skippedQuestions+=1
            //Log.d("!!!", "Skipped: $skippedQuestions")
        }
    }
    //Display Toast Timeup message for half second
    private fun displayTimeUpMessage(text: String, duration: Long)
    {
        val toast = Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT)
        toast.show()
        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                toast.cancel()
            }
        }, duration)
    }

    //Diplay next question if question number lessthan/equalto questionsList
    private fun displayNextQuestion(questionNumber: Int)
    {
        Log.d("!!!","Inside displayNext Question")
        Log.d("!!!","Question List by category size inside displaynext question : ${myQListByCategory.size}")
        if(questionNumber <= myQListByCategory.size){
            Log.d("!!!","Calling setQuestion inside displayNextQuestion")
            setQuestion()
        }
        else{
            Log.d("!!!", "Quiz completed!!")
        }
    }
    //Implement option buttons onClick functionality
    override fun onClick(view: View?) {
        //Cancel timer on option button click
       timer.cancel()
        //Set button click flag to true on Click
        optionButtonClickFlag = true
        //Check if current Question is not last question and display next button
        if(myCurrentQuestion!=myQListByCategory.size)
            next_floatButton.show()
        else
        //Display doneAll button in last question
            doneAll_floatButton.show()
        //Validate each option button on button click
        when(view?.id)
        {
            R.id.optionOneButton -> {
                selectedOptionValidate(optionOneButton, 1)
            }
            R.id.optionTwoButton -> {
                selectedOptionValidate(optionTwoButton, 2)
            }
            R.id.optionThreeButton -> {
                selectedOptionValidate(optionThreeButton, 3)
            }
            R.id.optionFourButton -> {
                selectedOptionValidate(optionFourButton, 4)
            }
        }

    }

    //Validate user selected option
    private fun selectedOptionValidate(selectedBtn: Button, selectedOptionNumber: Int) {
        val currentQuestionValidate = myQListByCategory!!.get(myCurrentQuestion - 1)

        //Display original Image
        val imgSource = currentQuestionValidate.imgName.toLowerCase() + "image"
        Log.d("!!!","Original Image Source: $imgSource")
        val resID = resources.getIdentifier(imgSource,"drawable",packageName)
        qv_imageView.setImageResource(resID)

        //Set all other options default
        defaultOptionsView()
        //Compare user selected option and correct answer and display button in Green color if correct
        if (selectedOptionNumber == currentQuestionValidate.correctAnswer) {
            selectedBtn.background =
                ContextCompat.getDrawable(this, R.drawable.correct_button_color)
            correctAnswered += 1
            score += 1
        } else {
            //Display button in Red Color if user selected option is wrong
            selectedBtn.background = ContextCompat.getDrawable(this, R.drawable.wrong_buton_color)
            wrongAnswered += 1
            answerDisplay(currentQuestionValidate.correctAnswer)

    }
        Log.d("!!!","Correct Answers: $correctAnswered")
         Log.d("!!!","Score: $score")
        Log.d("!!!","Wrong Answered: $wrongAnswered")
        Log.d("!!!","Skipped :$skippedQuestions")
    }

    //Display correct answer
    private fun answerDisplay(answer: Int)
    {
        //Log.d("!!!", "Inside answerDisplay function")
        when(answer)
        {
            1 -> {
                optionOneButton.background = ContextCompat.getDrawable(this, R.drawable.correct_button_color)
                manageButtonAnimation(optionOneButton)
            }

            2 -> {
                optionTwoButton.background = ContextCompat.getDrawable(this, R.drawable.correct_button_color)
                manageButtonAnimation(optionTwoButton)
            }

            3 -> {
                optionThreeButton.background = ContextCompat.getDrawable(this, R.drawable.correct_button_color)
                manageButtonAnimation(optionThreeButton)
            }

            4 -> {
                optionFourButton.background = ContextCompat.getDrawable(this, R.drawable.correct_button_color)
                manageButtonAnimation(optionFourButton)
            }
        }
    }

    private fun manageButtonAnimation(btn:Button)
    {
        val btnAnimation = AnimationUtils.loadAnimation(this, R.anim.blink)
        btn.startAnimation(btnAnimation)
    }

    //Back navigation button handling to Categories Activity
    override fun onBackPressed()
    {
        timer.cancel()
        val intent = Intent(this, CategoriesActivity::class.java)
        startActivity(intent)
        //Finish Question Activity
        finish()
    }

    private fun startResultsActivity()
    {
        val intent = Intent(this, ResultsActivity::class.java)

        //Pass data to Results Activity
        intent.putExtra("scoreValue", score)
        intent.putExtra("noOfQuestions", myQListByCategory.size)
        intent.putExtra("correctAnswered", correctAnswered)
        intent.putExtra("wrongAnswered", wrongAnswered)
        intent.putExtra("skipped", skippedQuestions)

        //Start Results activity
        startActivity(intent)
        //Finish Question Activity
        finish()
    }

}

