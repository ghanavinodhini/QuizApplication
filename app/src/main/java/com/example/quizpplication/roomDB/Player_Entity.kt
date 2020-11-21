package com.example.quizpplication.roomDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player_table")
data class Player_Entity (@ColumnInfo(name = "PlayerName")
                    val playerName:String)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "PlayerID")
    var playerid:Int?=null
}