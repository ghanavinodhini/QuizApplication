package com.example.quizpplication

import com.example.quizpplication.roomDB.Question_Entity

//Contains all data used in app
object DataManager
{
    lateinit var playerName:String
    const val helpMessage =
        "How to Play?\n" +
                "Player chooses a Category.\n"+
                "Colourless image will be displayed with options to find the image colour within 10 seconds.\n" +
                "Countdown timer starts immediately in all questions.\n"+
                "When player clicks Next arrow button, next question will be displayed.\n"+
                "Player can navigate back to Categories page from any question.\n"+
                "Results page displays the score details.\n"+
                "Player can restart or quit the quiz application from Results screen."
    //val categories = mutableListOf<Category>()
   // val questionsList = mutableListOf<Question>()
    val allQuestionsList = mutableListOf<Question_Entity>()

    init
    {
      //  createCategoryData()
       // createQuestionsData()
        insertQuestionsData()
    }

   /* private fun createCategoryData()
    {
        categories.add(Category("Fruits",R.drawable.fruits))
        categories.add(Category("Flowers",R.drawable.flowers))
        categories.add(Category("Vegetables",R.drawable.vegetables))
    }*/


     fun insertQuestionsData()
    {
        val que1 = Question_Entity("Guess The Color Of This Fruit",
            "Apple",
            "Blue",
            "Green",
            "Red",
            "Yellow",
            3,
            "Fruits")


        val que2 = Question_Entity("Guess The Color Of This Fruit",
            "Banana",
            "Green",
            "Red",
            "Brown",
            "Yellow",
            4,
            "Fruits")


        val que3 = Question_Entity("Guess The Color Of This Fruit",
            "Orange",
            "Purple",
            "Black",
            "Orange",
            "Yellow",
            3,
            "Fruits")


        val que4 = Question_Entity("Guess The Color Of This Fruit",
            "Kiwi",
            "Green",
            "Maroon",
            "Brown",
            "Pink",
            3,
            "Fruits")


        val que5 = Question_Entity("Guess The Color Of This Fruit",
            "DragonFruit",
            "Blue",
            "White",
            "Pink",
            "Yellow",
            3,
            "Fruits")

        allQuestionsList.add(que1)
        allQuestionsList.add(que2)
        allQuestionsList.add(que3)
        allQuestionsList.add(que4)
        allQuestionsList.add(que5)


        val que6 = Question_Entity("Guess The Color Of This Flower",
            "Rose",
            "Blue",
            "Green",
            "Red",
            "Yellow",
            3,
            "Flowers")

        val que7 = Question_Entity("Guess The Color Of This Flower",
            "Lotus",
            "Green",
            "Red",
            "Pink",
            "Yellow",
            3,
            "Flowers")


        val que8 = Question_Entity("Guess The Color Of This Flower",
            "Sunflower",
            "Purple",
            "Black",
            "Orange",
            "Yellow",
            4,
            "Flowers")


        val que9 = Question_Entity("Guess The Color Of This Flower",
            "Tulip",
            "Purple",
            "Maroon",
            "Brown",
            "Pink",
            1,
            "Flowers")


        val que10 = Question_Entity("Guess The Color Of This Flower",
            "Daisy",
            "Blue",
            "White",
            "Pink",
            "Yellow",
            2,
            "Flowers")

        allQuestionsList.add(que6)
        allQuestionsList.add(que7)
        allQuestionsList.add(que8)
        allQuestionsList.add(que9)
        allQuestionsList.add(que10)


        val que11 = Question_Entity("Guess The Color Of This Vegetable",
            "Beetroot",
            "Blue",
            "Green",
            "Purple",
            "Yellow",
            3,
            "Vegetables")

        val que12 = Question_Entity("Guess The Color Of This Vegetable",
            "Broccoli",
            "Pink",
            "Green",
            "Red",
            "Brown",
            2,
            "Vegetables")

        val que13 = Question_Entity("Guess The Color Of This Vegetable",
            "Capsicum",
            "Black",
            "Green",
            "Red",
            "Yellow",
            4,
            "Vegetables")

        val que14 = Question_Entity("Guess The Color Of This Vegetable",
            "Carrot",
            "Blue",
            "Orange",
            "Green",
            "Yellow",
            2,
            "Vegetables")

        val que15 = Question_Entity("Guess The Color Of This Vegetable",
            "Radish",
            "Red",
            "Green",
            "White",
            "Blue",
            3,
            "Vegetables")

        allQuestionsList.add(que11)
        allQuestionsList.add(que12)
        allQuestionsList.add(que13)
        allQuestionsList.add(que14)
        allQuestionsList.add(que15)

    }
}