package com.laironlf.smartRecipes.domain.models

import java.sql.Time
import java.time.Duration

open class Recipe (
    val id: Int,
    val title: String,
    val time: String,
    val kcal: Int,
    val ingredients: List<Ingredient>,
    var matchesProducts: List<Product>
){
    fun getMatchesCountAsString(): String{
        return "${matchesProducts.size} / ${ingredients.size}"
    }

    fun getTimeAsString(): String {
//        val time = Duration.parse(time)
        return ""
    }
}
