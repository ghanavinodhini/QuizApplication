package com.example.quizpplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_question.*

class QuizQuestionActivity : AppCompatActivity(),View.OnClickListener
{
    //Each time this Activity starts, we start with question 1
    var myCurrentQuestion:Int = 1
    var myQuestionsList=ArrayList<Question>()
    var mySelectedOptionPosition:Int = 0

   // var fruitsImage = findViewById<ImageView>(R.id.qv_imageView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        Log.d("!!!","In questions activity")

       // val questionsList = Constants.getQuestions()

        val qDisplay = Question()

         myQuestionsList = qDisplay.getQuestions()

        Log.d("!!!","No.of questions: ${myQuestionsList!!.size}")

        //Log.d("!!!","${questionsList.size}")

       setQuestion()


        //Adding Button choice Part
        optionOneButton.setOnClickListener(this)
        optionTwoButton.setOnClickListener(this)
        optionThreeButton.setOnClickListener(this)
        optionFourButton.setOnClickListener(this)

        //Adding Submit button
        submitButton.setOnClickListener(this)

    }

    fun setQuestion()
    {
       // myCurrentQuestion = 1
        val currentQuestionDisplay = myQuestionsList!!.get(myCurrentQuestion-1)
        defaultOptionsView()

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

    override fun onClick(v: View?) {
        when(v?.id)
        {
            //Adding Buttons choice part
            R.id.optionOneButton->{selectedOptionsView(optionOneButton,1)}
            R.id.optionTwoButton->{selectedOptionsView(optionTwoButton,2)}
            R.id.optionThreeButton->{selectedOptionsView(optionThreeButton,3)}
            R.id.optionFourButton->{selectedOptionsView(optionFourButton,4)}
            //Adding Submit button part
            R.id.submitButton->{}
        }
    }

    fun answerDisplay(answer:Int,drawableView:Int)
    {
        when(answer)
        {
            1->{optionOneButton.setBackgroundColor(R.drawable.correct_button_color)}
            2->{}
        }
    }

    fun selectedOptionsView(selectedBtn:Button,selectedOptionNumber:Int)
    {
        Log.d("!!!","Inside selectedOptionsView function")
        //Set all other options default
        defaultOptionsView()
        mySelectedOptionPosition = selectedOptionNumber
        //to set selected border for selected button choice
        selectedBtn.background = ContextCompat.getDrawable(this,R.drawable.selected_option_border_bg)
    }
}

