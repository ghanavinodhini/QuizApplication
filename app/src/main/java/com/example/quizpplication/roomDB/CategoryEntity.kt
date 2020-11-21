package com.example.quizpplication.roomDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//This data class contains Category table and columns
@Entity(tableName = "category_table")
data class CategoryEntity(@ColumnInfo(name = "Category_Description")
                    var description:String)
{
    @PrimaryKey(autoGenerate = true)
    var category_id:Int?=null
}