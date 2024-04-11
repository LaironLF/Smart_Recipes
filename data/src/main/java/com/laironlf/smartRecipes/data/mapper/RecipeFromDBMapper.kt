package com.laironlf.smartRecipes.data.mapper

import com.laironlf.smartRecipes.data.room.entity.MeasureTypeDB
import com.laironlf.smartRecipes.data.room.entity.ProductDB
import com.laironlf.smartRecipes.data.room.entity.RecipeDB
import com.laironlf.smartRecipes.data.room.entity.RecipeIngredientDB
import com.laironlf.smartRecipes.data.room.entity.RecipeStepDB
import com.laironlf.smartRecipes.domain.models.Ingredient
import com.laironlf.smartRecipes.domain.models.Product
import com.laironlf.smartRecipes.domain.models.Recipe
import com.laironlf.smartRecipes.domain.models.RecipeStep

fun RecipeDB.mapToRecipe(): Recipe {
    return Recipe(
        id = this.id,
        title = this.title,
        time = this.time,
        kcal = this.kkal,
        ingredients = emptyList(),
        matchesProducts = emptyList(),
        imageUrl = "null"
    )
}

fun Recipe.mapToDB(): RecipeDB = RecipeDB(
    id = this.id,
    title = this.title,
    time = this.time,
    kkal = this.kcal
)

fun ProductDB.mapToProduct(): Product {
    return Product(
        id = this.id,
        title = this.title
    )
}

fun Product.mapToDB(): ProductDB {
    return ProductDB(
        id = this.id,
        title = this.title
    )
}

fun RecipeIngredientDB.mapToIngredient(product: Product, measureTypeDB: MeasureTypeDB): Ingredient {
    return Ingredient(
        product = product,
        count = this.count,
        measureType = this.measureType
    )
}

fun Ingredient.mapToDB(recipeId: Int) = RecipeIngredientDB(
    id = 0,
    id_Recipe = recipeId,
    id_Product = this.product.id,
    measureType = this.measureType,
    count = this.count
)

fun RecipeStepDB.mapToRecipeStep(): RecipeStep {
    return RecipeStep(
        step = this.step_num,
        text = this.step_text,
        image = null
    )
}

fun RecipeStep.mapToDB(recipeId: Int): RecipeStepDB = RecipeStepDB(
    id = 0,
    id_Recipe = recipeId,
    step_num = this.step,
    step_text = this.text,
    step_image = this.image?: ""

)
