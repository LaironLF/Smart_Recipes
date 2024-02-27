package com.laironlf.smartRecipes.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserProductDB (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val id_Product: Int
)