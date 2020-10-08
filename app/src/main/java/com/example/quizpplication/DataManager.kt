package com.example.quizpplication

object DataManager {
    lateinit var playerName:String
    val helpMessage =
        "How to Play?\n" +
                "Player chooses a Category.\n"+
                "Colourless image will be displayed with options to find the image colour within 10 seconds.\n" +
                "Countdown timer starts immediately in all questions.\n"+
                "When player clicks Next arrow button, next question will be displayed.\n"+
                "Player can navigate back to Categories page from any question.\n"+
                "Results page displays the score details.\n"+
                "Player can restart or quit the quiz application from Results screen."
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