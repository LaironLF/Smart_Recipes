package com.laironlf.smartRecipes.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecipeIngredientDB(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val id_Recipe: Int,
    val id_Product: Int,
    val measureType: String,
    val count: Int,
)
