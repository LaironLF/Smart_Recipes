package com.laironlf.smartRecipes.domain.models

data class Ingredient (
    val product: Product,
    val count : Int,
    val measureType : String
)