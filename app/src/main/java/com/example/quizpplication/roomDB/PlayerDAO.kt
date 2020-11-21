package com.example.quizpplication.roomDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface PlayerDAO {

    @Insert
    suspend fun insertPlayer(vararg player: Player_Entity)

    @Query("DELETE FROM player_table")
    suspend fun deleteAllPlayer()

    @Query("SELECT playerName FROM player_table")
    suspend fun getPlayer() : String
}