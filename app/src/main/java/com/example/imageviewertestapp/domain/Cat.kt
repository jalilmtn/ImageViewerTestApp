package com.example.imageviewertestapp.domain

import androidx.compose.runtime.Stable

@Stable
data class Cat(
    val id: String,
    val url:String,
    val width : Int,
    val height: Int
)
