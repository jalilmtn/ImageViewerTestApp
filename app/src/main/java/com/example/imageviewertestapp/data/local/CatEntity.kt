package com.example.imageviewertestapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CatEntity(
    @PrimaryKey
    val id: String,
    val url:String,
    val width : Int,
    val height: Int
)
