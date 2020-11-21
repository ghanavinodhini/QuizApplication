package com.example.quizpplication.roomDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.quizpplication.Question

@Dao
interface QuestionDAO {
    @Insert
    fun insertQuestion(vararg question : Question_Entity)

    @Delete
    fun delete(question: Question_Entity)

    @Query("DELETE FROM Questions_table")
    fun deleteAll()

    @Query("SELECT * FROM Questions_table")
    fun getAllQuestions():List<Question_Entity>

    @Query("SELECT * FROM Questions_table WHERE Question_Category LIKE:categoryName")
    fun getQuestionsByCategory(categoryName: String):List<Question_Entity>
}