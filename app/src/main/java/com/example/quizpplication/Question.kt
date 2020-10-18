package com.example.quizpplication

//this class contains Questions data
 class Question (val id:Int? = null,
                 val image_grayscale:Int?=null,
                 val image:Int? = null,
                 val optionOne:String? = null,
                 val optionTwo:String? = null,
                 val optionThree:String? = null,
                 val optionFour:String? = null,
                 val correctAnswer:Int = 0,
                 val categoryType:String?=null)

{
    //this function returns questions list based on category type
    fun getQuestions(categoryName:String) : MutableList<Question>
    {
        val retQuestionList = mutableListOf<Question>()
        val qList = DataManager.questionsList

        for (i in 0..qList.size-1){
            if(qList[i].categoryType==categoryName) {
                retQuestionList.add(qList[i])
            }
        }
        return retQuestionList
    }
}