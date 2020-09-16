package com.example.quizpplication

//this data class contains only data information and no functions
 class Question (val id:Int? = null,
                     val image:Int? = null,
                     val optionOne:String? = null,
                     val optionTwo:String? = null,
                     val optionThree:String? = null,
                     val optionFour:String? = null,
                     val correctAnswer:Int? = 0)

{
    fun getQuestions():ArrayList<Question>
    {
        val questionsList = ArrayList<Question>()

        val que1 = Question(1,
                             R.drawable.appleimage,
                            "Blue",
                            "Green",
                            "Red",
                            "Yellow",
                            3)


        val que2 = Question(2,
                             R.drawable.bananaimage,
                            "Green",
                            "Red",
                            "Brown",
                            "Yellow",
                            4)


        val que3 = Question(3,
                             R.drawable.orangeimage,
                            "Purple",
                            "Black",
                            "Orange",
                            "Yellow",
                            3)


        val que4 = Question(4,
                             R.drawable.kiwiimage,
                            "Green",
                            "Maroon",
                            "Brown",
                            "Pink",
                            1)


        val que5 = Question(5,
                             R.drawable.dragonfruitimage,
                            "Blue",
                            "White",
                            "Pink",
                            "Yellow",
                            3)
        questionsList.add(que1)
        questionsList.add(que2)
        questionsList.add(que3)
        questionsList.add(que4)
        questionsList.add(que5)

        return questionsList
    }
}