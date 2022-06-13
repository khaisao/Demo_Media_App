package com.example.myapplication.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class MMediaFile(
    var absolutePath: String="",
    @ColumnInfo
    var title: String

):Serializable{
    @PrimaryKey(autoGenerate = true)
    var id = 0
}
