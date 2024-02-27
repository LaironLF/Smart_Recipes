package com.laironlf.smartRecipes.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MeasureTypeDB(
    @PrimaryKey
    val id: Int,
    val title: String
)