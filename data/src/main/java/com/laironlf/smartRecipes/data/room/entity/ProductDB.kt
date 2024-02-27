package com.laironlf.smartRecipes.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductDB(
    @PrimaryKey
    val id: Int,
    val title: String
)
