package com.example.quizpplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_question.*

class QuizQuestionActivity : AppCompatActivity(),View.OnClickListener
{
    //Each time this Activity starts first time, we start with question 1
    var myCurrentQuestion:Int = 1
    //Create instance of class Question as List variable to hold all questions details
    var myQuestionsList= mutableListOf<Question>()
    //Set variable to check if any options selected in question
    var mySelectedOptionPosition:Int = 0
    //Set variable to hold correctly answered
    var correctAnswered = 0
    //Set variable to hold wrongly answered
    var wrongAnswered = 0
    //Set score value
    var score = 0
    //Set flag variable to deactivate buttons after click
    var optionButtonClickFlag = false
    //Create a varibale to hold player Name
    private var playerName:String?=null
    var backCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        Log.d("!!!","In questions activity")

        //Assign the value of player Name to variable
        playerName = getIntent().getStringExtra("playerName").toString()
        //Create instance of class Question to access its members and functions
        val qDisplay = Question()
        //Retreive all list of questions
         myQuestionsList = qDisplay.getQuestions()

        Log.d("!!!","No.of questions: ${myQuestionsList!!.size}")

        //Set first question
        setQuestion()

        //Set OnclickListener event for all 'option' buttons
        optionOneButton.setOnClickListener(this)
        optionTwoButton.setOnClickListener(this)
        optionThreeButton.setOnClickListener(this)
        optionFourButton.setOnClickListener(this)

        //Set OnClickListener for 'next' button
        next_floatButton.setOnClickListener{
            Log.d("!!!","next button pressed!!")
                Log.d("!!!","Entering incrementing question")
                optionButtonClickFlag = false
                myCurrentQuestion++
                displayNextQuestion(myCurrentQuestion)
            }
        //Set OnClickListener for 'doneAll' button
        doneAll_floatButton.setOnClickListener{
            Log.d("!!!","Done All button pressed!")
            //Start Results Activity on doneAll button click
            startResultsActivity()
        }
    }

    //Diplay next question if question number lessthan/equalto questionsList
    fun displayNextQuestion(questionNumber:Int)
    {
        if(questionNumber <= myQuestionsList.size){
            setQuestion()
        }
        else{
            Log.d("!!!","Quiz completed!!")
        }
    }

    //Display Questions Details
    fun setQuestion()
    {
        //Get current Question details from questionsList
        val currentQuestionDisplay = myQuestionsList!!.get(myCurrentQuestion-1)
        //Set default border for option buttons
        defaultOptionsView()
        //Hide next button
        next_floatButton.hide()
        //Hide doneAll Button
        doneAll_floatButton.hide()

        Log.d("!!!","Current Question details: ${currentQuestionDisplay.id},${currentQuestionDisplay.image}")
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

    fun defaultOptionsView()
    {
        Log.d("!!!","Inside defaultOptionsView function")

        //Create list variable for buttons and add all buttons
        val options = mutableListOf<Button>()
        options.add(0,optionOneButton)
        options.add(1,optionTwoButton)
        options.add(2,optionThreeButton)
        options.add(3,optionFourButton)

        for(opt in options)
        {
            //To set option border by default for unselected button choices
            opt.background = ContextCompat.getDrawable(this,R.drawable.option_border_bg)
            //Enable and Disable buttons based on flag value
            if(!optionButtonClickFlag)
                opt.isEnabled = true
            else opt.isEnabled = false
        }
    }

    //Implement option buttons onClick functionality
    override fun onClick(view: View?)
    {
        //Set button click flag to true on Click
        optionButtonClickFlag = true
        //Check if current Question is not last question and display next button
        if(myCurrentQuestion!=myQuestionsList.size)
        next_floatButton.show()
        else
            //Display doneAll button in last question
            doneAll_floatButton.show()

        when(view?.id)
        {
            //Validate each option button on button click
            R.id.optionOneButton->{selectedOptionValidate(optionOneButton,1)}
            R.id.optionTwoButton->{selectedOptionValidate(optionTwoButton,2)}
            R.id.optionThreeButton->{selectedOptionValidate(optionThreeButton,3)}
            R.id.optionFourButton->{selectedOptionValidate(optionFourButton,4)}

        }
    }


    //Validate user selected option
    fun selectedOptionValidate(selectedBtn:Button,selectedOptionNumber:Int)
    {
        val currentQuestionValidate = myQuestionsList!!.get(myCurrentQuestion-1)

        //Set to original image
        currentQuestionValidate.image?.let { qv_imageView.setImageResource(it) }
        //Set all other options default
        defaultOptionsView()
       //mySelectedOptionPosition = selectedOptionNumber

        //Compare user selected option and correct answer and display button in Green color if correct
        if(selectedOptionNumber==currentQuestionValidate.correctAnswer)
        {
            selectedBtn.background = ContextCompat.getDrawable(this, R.drawable.correct_button_color)
                correctAnswered += 1
                score += 1
        }
        else{
            //Display button in Red Color if user selected option is wrong
            selectedBtn.background = ContextCompat.getDrawable(this,R.drawable.wrong_buton_color)
                wrongAnswered+=1
            answerDisplay(currentQuestionValidate.correctAnswer)
        }
        //mySelectedOptionPosition = selectedOptionNumber

    }

    //Display correct answwer
    fun answerDisplay(answer:Int)
    {
        Log.d("!!!","Inside answerDisplay function")
        when(answer)
        {
            1->optionOneButton.background = ContextCompat.getDrawable(this,R.drawable.correct_button_color)
            2->optionTwoButton.background = ContextCompat.getDrawable(this,R.drawable.correct_button_color)
            3->optionThreeButton.background = ContextCompat.getDrawable(this,R.drawable.correct_button_color)
            4->optionFourButton.background = ContextCompat.getDrawable(this,R.drawable.correct_button_color)
        }
    }

    fun startResultsActivity()
    {
        val intent = Intent(this,ResultsActivity::class.java)

        //Pass data to Results Activity
        intent.putExtra("scoreValue",score)
        intent.putExtra("noOfQuestions",myQuestionsList.size)
        intent.putExtra("correctAnswered",correctAnswered)
        intent.putExtra("wrongAnswered",wrongAnswered)
        intent.putExtra("playerName",playerName)
        //Start Results activity
        startActivity(intent)
        //Finish Results Activity
        finish()
    }

    //Back navigation button handling
    override fun onBackPressed() {
        backCount++

        if(backCount == 1)
        {
            Log.d("!!!","pressed once")
            Toast.makeText(this,"Press again to exit", Toast.LENGTH_LONG).show()
        }
        if(backCount == 2) {
            Log.d("!!!", "Back button pressed twice")
            super.onBackPressed()
            return
        }
    }
}

