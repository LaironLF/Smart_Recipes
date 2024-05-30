package com.laironlf.smartRecipes.domain.models.post

import com.laironlf.smartRecipes.domain.models.MeasureType
import com.laironlf.smartRecipes.domain.models.Product


data class IngredientPost(
    val measureType: MeasureType,
    val product: Product,
    val quantity: Int
)