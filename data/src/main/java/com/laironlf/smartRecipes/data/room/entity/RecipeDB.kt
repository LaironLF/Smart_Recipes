package com.laironlf.smartRecipes.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time

@Entity
data class RecipeDB(
    @PrimaryKey
    val id: Int,
    val title: String,
    val time: String,
    val kkal: Int,
)
