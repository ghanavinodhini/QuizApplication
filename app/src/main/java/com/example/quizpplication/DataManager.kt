package com.example.quizpplication

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
    val categories = mutableListOf<Category>()
    val questionsList = mutableListOf<Question>()

    init {
        createCategoryData()
        createQuestionsData()
    }

    private fun createCategoryData()
    {
        categories.add(Category("Fruits",R.drawable.fruits))
        categories.add(Category("Flowers",R.drawable.flowers))
        categories.add(Category("Vegetables",R.drawable.vegetables))
    }

    private fun createQuestionsData()
    {
        val que1 = Question(1,
                            R.drawable.appleimage_grayscale,
                            R.drawable.appleimage,
                            "Blue",
                            "Green",
                            "Red",
                            "Yellow",
                            3,
                            "Fruits")


        val que2 = Question(2,
                            R.drawable.bananaimage_grayscale,
                            R.drawable.bananaimage,
                            "Green",
                            "Red",
                            "Brown",
                            "Yellow",
                            4,
                            "Fruits")


        val que3 = Question(3,
                            R.drawable.orangeimage_grayscale,
                            R.drawable.orangeimage,
                            "Purple",
                            "Black",
                            "Orange",
                            "Yellow",
                            3,
                            "Fruits")


        val que4 = Question(4,
                            R.drawable.kiwiimage_grayscale,
                            R.drawable.kiwiimage,
                            "Green",
                            "Maroon",
                            "Brown",
                            "Pink",
                            3,
                            "Fruits")


        val que5 = Question(5,
                            R.drawable.dragonfruitimage_grayscale,
                            R.drawable.dragonfruitimage,
                            "Blue",
                            "White",
                            "Pink",
                            "Yellow",
                            3,
                            "Fruits")

        questionsList.add(que1)
        questionsList.add(que2)
        questionsList.add(que3)
        questionsList.add(que4)
        questionsList.add(que5)

        val que6 = Question(6,
                            R.drawable.rose_grayscale,
                            R.drawable.roseimage,
                            "Blue",
                            "Green",
                            "Red",
                            "Yellow",
                            3,
                            "Flowers")

        val que7 = Question(7,
                            R.drawable.lotus_grayscale,
                            R.drawable.lotusimage,
                            "Green",
                            "Red",
                            "Pink",
                            "Yellow",
                            3,
                            "Flowers")


        val que8 = Question(8,
                            R.drawable.sunflower_grayscale,
                            R.drawable.sunflowerimage,
                            "Purple",
                            "Black",
                            "Orange",
                            "Yellow",
                            4,
                            "Flowers")


        val que9 = Question(9,
                            R.drawable.tulip_grayscale,
                            R.drawable.tulipimage,
                            "Purple",
                            "Maroon",
                            "Brown",
                            "Pink",
                            1,
                            "Flowers")


        val que10 = Question(10,
                            R.drawable.daisy_grayscale,
                            R.drawable.daisyimage,
                            "Blue",
                            "White",
                            "Pink",
                            "Yellow",
                            2,
                            "Flowers")

        questionsList.add(que6)
        questionsList.add(que7)
        questionsList.add(que8)
        questionsList.add(que9)
        questionsList.add(que10)

        val que11 = Question(11,
                            R.drawable.beetroot_grayscale,
                            R.drawable.beetrootimage,
                            "Blue",
                            "Green",
                            "Purple",
                            "Yellow",
                            3,
                            "Vegetables")

        val que12 = Question(12,
                            R.drawable.broccoli_grayscale,
                            R.drawable.broccoliimage,
                            "Pink",
                            "Green",
                            "Red",
                            "Brown",
                            2,
                            "Vegetables")

        val que13 = Question(13,
                            R.drawable.capsicum_grayscale,
                            R.drawable.capsicumimage,
                            "Black",
                            "Green",
                            "Red",
                            "Yellow",
                            4,
                            "Vegetables")

        val que14 = Question(14,
                            R.drawable.carrot_grayscale,
                            R.drawable.carrotimage,
                            "Blue",
                            "Orange",
                            "Green",
                            "Yellow",
                            2,
                            "Vegetables")

        val que15 = Question(15,
                            R.drawable.radish_grayscale,
                            R.drawable.radishimage,
                            "Red",
                            "Green",
                            "White",
                            "Blue",
                            3,
                            "Vegetables")

        questionsList.add(que11)
        questionsList.add(que12)
        questionsList.add(que13)
        questionsList.add(que14)
        questionsList.add(que15)
    }
}