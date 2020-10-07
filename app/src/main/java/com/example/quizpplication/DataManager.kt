package com.example.quizpplication

import android.util.Log
import android.widget.Toast

//singleton
object DataManager {

    //Create variable for PLayername
    lateinit var playerName:String
    var helpMessage =
        "This quiz application contains categories to choose. On selecting a category displays questions with colourless image to find the color within given time. " +
                "Choices will be displayed to select the correct colour. The original image will be displayed after user selection option or timeout. The next arrow button navigates to next question."+
                "The results page displays the score details. Player can restart or quit the quiz from Results screen."
    val categories = mutableListOf<Category>()

    init {
        createCategoryData()
    }

    fun createCategoryData()
    {
        categories.add(Category("Fruits",R.drawable.fruits))
        categories.add(Category("Flowers",R.drawable.flowers))
    }
}