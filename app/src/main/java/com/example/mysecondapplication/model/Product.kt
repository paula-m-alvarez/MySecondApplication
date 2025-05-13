package com.example.mysecondapplication.model

import androidx.annotation.DrawableRes

data class Product(
    @DrawableRes val imageRes: Int,
    val title: String,
    val price: String,
    val description: String
)
