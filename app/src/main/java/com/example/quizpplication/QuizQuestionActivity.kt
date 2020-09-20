package com.example.quizpplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_question.*

class QuizQuestionActivity : AppCompatActivity(),View.OnClickListener
{
    //Each time this Activity starts first time, we start with question 1
    var myCurrentQuestion:Int = 1
    //Create instance of Question as List variable to hold all questions details
    var myQuestionsList=ArrayList<Question>()
    //Set variable to check if any options selected in question
    var mySelectedOptionPosition:Int = 0
    //Set variable to hold correctly answered
    var correctAnswered = 0
    //Set variable to hold wrongly answered
    var wrongAnswered = 0
    //Set score value
    var score = 0
    //Assign playername
    lateinit var player:String

   // var fruitsImage = findViewById<ImageView>(R.id.qv_imageView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        Log.d("!!!","In questions activity")

        player = getIntent().getStringExtra("playerName").toString()
        val qDisplay = Question()

         myQuestionsList = qDisplay.getQuestions()

        Log.d("!!!","No.of questions: ${myQuestionsList!!.size}")


       setQuestion()


        //Adding Button choice Part
        optionOneButton.setOnClickListener(this)
        optionTwoButton.setOnClickListener(this)
        optionThreeButton.setOnClickListener(this)
        optionFourButton.setOnClickListener(this)

        //Adding next button click
        next_floatButton.setOnClickListener{
            Log.d("!!!","next button pressed!!")

                Log.d("!!!","Entering incrementing question")
                myCurrentQuestion++
                displayNextQuestion(myCurrentQuestion)

            }
        doneAll_floatButton.setOnClickListener{
            Log.d("!!!","Done All button pressed!")
            startResultsActivity()
        }
    }

    fun displayNextQuestion(questionNumber:Int)
    {
        if(questionNumber <= myQuestionsList.size){
            setQuestion()
        }
        else{
            Log.d("!!!","Quiz completed!!")

        }


    }

    fun setQuestion()
    {
       // myCurrentQuestion = 1
        val currentQuestionDisplay = myQuestionsList!!.get(myCurrentQuestion-1)
        defaultOptionsView()
        next_floatButton.hide()
        doneAll_floatButton.hide()

        Log.d("!!!","Current Question details: ${currentQuestionDisplay.id},${currentQuestionDisplay.image}")
        progressBar.progress = myCurrentQuestion
        progressTextView.text = "$myCurrentQuestion" + "/" + progressBar.max


        //To set image for current question
       currentQuestionDisplay.image?.let { qv_imageView.setImageResource(it) }

       // currentQuestionDisplay.image?.let{fruitsImage.setImageResource(currentQuestionDisplay.image)}


        //Adding Button choices Part
        optionOneButton.text = currentQuestionDisplay.optionOne
        optionTwoButton.text = currentQuestionDisplay.optionTwo
        optionThreeButton.text = currentQuestionDisplay.optionThree
        optionFourButton.text = currentQuestionDisplay.optionFour
    }

    fun defaultOptionsView()
    {
        Log.d("!!!","Inside defaultOptionsView function")

        //Adding Button choices part
        val options = mutableListOf<Button>()
        options.add(0,optionOneButton)
        options.add(1,optionTwoButton)
        options.add(2,optionThreeButton)
        options.add(3,optionFourButton)

        //set default values for unselected button options
        for(opt in options)
        {
            //To set option border by default for unselected button choices
            opt.background = ContextCompat.getDrawable(this,R.drawable.option_border_bg)
        }
    }

    override fun onClick(view: View?) {
        if(myCurrentQuestion!=myQuestionsList.size)
        next_floatButton.show()
        else
            doneAll_floatButton.show()

        when(view?.id)
        {
            //Adding Buttons choice part
            R.id.optionOneButton->{selectedOptionValidate(optionOneButton,1)}
            R.id.optionTwoButton->{selectedOptionValidate(optionTwoButton,2)}
            R.id.optionThreeButton->{selectedOptionValidate(optionThreeButton,3)}
            R.id.optionFourButton->{selectedOptionValidate(optionFourButton,4)}

        }
    }

    fun answerDisplay(answer:Int)
    {
        Log.d("!!!","Inside answerDisplay function")
       when(answer){
           1->optionOneButton.background = ContextCompat.getDrawable(this,R.drawable.correct_button_color)
           2->optionTwoButton.background = ContextCompat.getDrawable(this,R.drawable.correct_button_color)
           3->optionThreeButton.background = ContextCompat.getDrawable(this,R.drawable.correct_button_color)
           4->optionFourButton.background = ContextCompat.getDrawable(this,R.drawable.correct_button_color)
       }


    }

    fun selectedOptionValidate(selectedBtn:Button,selectedOptionNumber:Int)
    {
        val currentQuestionValidate = myQuestionsList!!.get(myCurrentQuestion-1)
        //Set all other options default
        defaultOptionsView()
       //mySelectedOptionPosition = selectedOptionNumber

        if(selectedOptionNumber==currentQuestionValidate.correctAnswer)
        {
            selectedBtn.background = ContextCompat.getDrawable(this, R.drawable.correct_button_color)
            correctAnswered++
            score++
        }
        else{
            selectedBtn.background = ContextCompat.getDrawable(this,R.drawable.wrong_buton_color)
            wrongAnswered++
            answerDisplay(currentQuestionValidate.correctAnswer)
        }
        mySelectedOptionPosition = selectedOptionNumber

    }

    fun startResultsActivity()
    {
        val intent = Intent(this,ResultsActivity::class.java)

        intent.putExtra("scoreValue",score)
        intent.putExtra("noOfQuestions",myQuestionsList.size)
        intent.putExtra("correctAnswered",correctAnswered)
        intent.putExtra("wrongAnswered",wrongAnswered)
        intent.putExtra("playerName",player)
        //Start Results activity
        startActivity(intent)

    }
}

