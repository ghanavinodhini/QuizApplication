package com.example.quizpplication.roomDB

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


//This class contains Database for Questions
@Database(entities = [Question_Entity::class, CategoryEntity::class, Player_Entity::class],version = 2,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val questionDao: QuestionDAO
    abstract val categoryDao: CategoryDAO
    abstract val playerDao:PlayerDAO

    companion object {

        //For Singleton instantiation of DB
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            Log.d("!!!", "Inside Database companion object getInstance()")
            synchronized(this)
            {
                var instance: AppDatabase? = INSTANCE
                Log.d("!!!", "instance value : $instance")
                if (instance == null) {
                    Log.d("!!!", "instance value inside if : $instance")
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "GuessQuizDatabase"
                    )

                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance

                }
                Log.d("!!!", "instance value outside if : $instance")
                return instance
            }
        }
    }
}