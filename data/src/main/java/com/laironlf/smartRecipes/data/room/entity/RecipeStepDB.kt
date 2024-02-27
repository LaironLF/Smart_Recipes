package com.laironlf.smartRecipes.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecipeStepDB(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val id_Recipe: Int,
    val step_num: Int,
    val step_text: String,
    val step_image: String
)
