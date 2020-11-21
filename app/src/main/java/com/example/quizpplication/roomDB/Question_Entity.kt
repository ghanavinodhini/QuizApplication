package com.example.quizpplication.roomDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Questions_table")
data class Question_Entity (
    @ColumnInfo(name = "Question")
    val question:String,

    @ColumnInfo(name = "Option_One")
    val optionOne:String? = null,

    @ColumnInfo(name = "Option_Two")
    val optionTwo:String? = null,

    @ColumnInfo(name = "Option_Three")
    val optionThree:String? = null,

    @ColumnInfo(name = "Option_Four")
    val optionFour:String? = null,

    @ColumnInfo(name = "Correct_Answer")
    val correctAnswer:Int = 0,

    @ColumnInfo(name = "Question_Category")
    val categoryType:String? = null)

{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "QuestionID")
    var que_id:Int?=null
}
