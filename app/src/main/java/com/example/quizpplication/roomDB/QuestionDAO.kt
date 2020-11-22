package com.example.quizpplication.roomDB

import androidx.room.*


@Dao
interface QuestionDAO {
    @Insert
    suspend fun insertQuestion(vararg question : Question_Entity)

    @Delete
    suspend fun delete(question: Question_Entity)

    @Query("DELETE FROM Questions_table")
   suspend fun deleteAllQuestions()

    @Query("SELECT * FROM Questions_table")
    suspend fun getAllQuestions():List<Question_Entity>

    @Query("SELECT * FROM Questions_table WHERE Question_Category LIKE:categoryName")
    suspend fun getQuestionsByCategory(categoryName: String):List<Question_Entity>
}