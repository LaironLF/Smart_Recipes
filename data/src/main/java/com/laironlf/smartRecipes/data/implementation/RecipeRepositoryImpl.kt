package com.laironlf.smartRecipes.data.implementation

import android.content.Context
import com.laironlf.smartRecipes.data.api.RecipesApiService
import com.laironlf.smartRecipes.data.mapper.mapToDB
import com.laironlf.smartRecipes.data.mapper.mapToIngredient
import com.laironlf.smartRecipes.data.mapper.mapToRecipe
import com.laironlf.smartRecipes.data.room.AppRecipesDatabase
import com.laironlf.smartRecipes.data.room.entity.UserRecipeDB
import com.laironlf.smartRecipes.domain.models.params.GetRecipesListParams
import com.laironlf.smartRecipes.domain.models.params.GetRecipesListParams.FetchType
import com.laironlf.smartRecipes.domain.models.Ingredient
import com.laironlf.smartRecipes.domain.models.Recipe
import com.laironlf.smartRecipes.domain.models.RecipeStep
import com.laironlf.smartRecipes.domain.models.params.AddNewRecipeParams
import com.laironlf.smartRecipes.domain.models.params.SaveRecipeParams
import com.laironlf.smartRecipes.domain.repository.RecipeRepository

class RecipeRepositoryImpl(
    private val api: RecipesApiService,
    context: Context
) : RecipeRepository {
    private val roomDBDao = AppRecipesDatabase.getDataBase(context).getRoomDBDao()

    override suspend fun getRecipesList(params: GetRecipesListParams?): List<Recipe> =
        when (params?.fetchType){
            FetchType.All -> api.getRecipes(null).map { it.mapToRecipe() }
            FetchType.Saved -> getSavedRecipesList()
            FetchType.Now -> getFilteredRecipesList(params!!.productsId!!)
            else -> api.getRecipes(null).map { it.mapToRecipe() }
        }


    private suspend fun getSavedRecipesList(): List<Recipe> =
        roomDBDao.getRecipes().map { it.mapToRecipe() }

    private suspend fun getFilteredRecipesList(productIds: List<Int>): List<Recipe> =
        if(productIds.isNotEmpty()){
            api.getRecipes(productIds).map { it.mapToRecipe() }
        }  else {
            emptyList()
        }

    override suspend fun getRecipeSteps(id: Int): List<RecipeStep> {
        return api.getRecipeSteps(id).map {
            it.mapToRecipe()
        }
    }

    override suspend fun getRecipeIngredients(id: Int): List<Ingredient> {
        return api.getRecipeIngredients(id).map {
            it.mapToIngredient()
        }
    }

    override suspend fun getRecipeById(id: Int): Recipe {
        return api.getRecipe(id).mapToRecipe()
    }

    override suspend fun saveRecipe(params: SaveRecipeParams) {
        val id = params.recipe.id
        roomDBDao.addRecipe(params.recipe.mapToDB())
        roomDBDao.addRecipeIngredients(params.recipe.ingredients.map { it.mapToDB(id) })
        roomDBDao.addRecipeSteps(params.recipeSteps.map { it.mapToDB(id) })
        if (params.saveToUserRecipes) roomDBDao.addRecipeToUserList(UserRecipeDB(0, id))
    }

    override suspend fun addNewRecipe(params: AddNewRecipeParams) {
        TODO("Not yet implemented")
    }

}