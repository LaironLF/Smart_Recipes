package com.laironlf.smartRecipes.domain.models

data class RecipeDetailInfo(
    val id: Int,
    val title: String,
    val time: String,
    val kkal: String,
    val productsTotal: Int,
    val desciption: String,
//    val ingredients: List<Ingredient>,
//    val steps: List<RecipeStep>
)