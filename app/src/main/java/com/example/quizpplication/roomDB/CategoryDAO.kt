package com.example.quizpplication.roomDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CategoryDAO {

    @Insert
    suspend fun insertCategory(vararg category: CategoryEntity)

    @Query("DELETE FROM category_table")
    suspend fun deleteAllCategories()

    @Query("SELECT * FROM category_table")
    suspend fun getAllCategories():List<CategoryEntity>
}