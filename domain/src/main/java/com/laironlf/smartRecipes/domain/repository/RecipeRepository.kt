package com.laironlf.smartRecipes.domain.repository

import com.laironlf.smartRecipes.domain.models.params.AddNewRecipeParams
import com.laironlf.smartRecipes.domain.models.params.GetRecipesListParams
import com.laironlf.smartRecipes.domain.models.Ingredient
import com.laironlf.smartRecipes.domain.models.Recipe
import com.laironlf.smartRecipes.domain.models.RecipeStep
import com.laironlf.smartRecipes.domain.models.params.LikeRecipeParams

interface RecipeRepository {
    suspend fun getRecipesList(params: GetRecipesListParams?): List<Recipe>
    suspend fun getRecipeSteps(id: Int): List<RecipeStep>
    suspend fun getRecipeIngredients(id: Int): List<Ingredient>

    suspend fun getRecipeById(id: Int): Recipe

    /**
     * Сохранение рецепта в "Избранное" пользователя
     */
    suspend fun saveRecipeIntoStorage(params: LikeRecipeParams)
    suspend fun removeRecipeFromStorage(params: LikeRecipeParams)

    suspend fun getRecipeFromStorage(idRecipe: Int)

    /**
     * Добавление нового рецепта на сервер
     */
    suspend fun addNewRecipe(params: AddNewRecipeParams)
}