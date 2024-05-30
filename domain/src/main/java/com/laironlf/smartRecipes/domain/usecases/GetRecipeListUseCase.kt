package com.laironlf.smartRecipes.domain.usecases

import com.laironlf.smartRecipes.domain.models.params.GetProductListParams
import com.laironlf.smartRecipes.domain.models.params.GetRecipesListParams
import com.laironlf.smartRecipes.domain.models.params.GetRecipesListParams.FetchType
import com.laironlf.smartRecipes.domain.models.Recipe
import com.laironlf.smartRecipes.domain.repository.ProductRepository
import com.laironlf.smartRecipes.domain.repository.RecipeRepository

class GetRecipeListUseCase(
    private val recipeRepository: RecipeRepository,
    private val productRepository: ProductRepository

) {
    suspend operator fun invoke(getRecipesListParams: GetRecipesListParams): List<Recipe>{
        val userProducts = productRepository.getUserProductList()
        if (getRecipesListParams.fetchType is FetchType.Now && getRecipesListParams.productsId == null)
            getRecipesListParams.productsId = userProducts.map { it.id }

        val recipes = recipeRepository.getRecipesList(getRecipesListParams)
        recipes.map { recipe ->
            recipe.matchesProducts = userProducts.filter { product ->
                recipe.ingredients.any { ingredient ->
                    ingredient.product.id == product.id
                }
            }
        }
        val userRecipes = recipeRepository.getRecipesList(GetRecipesListParams(FetchType.Saved))
        recipes.forEach{ recipe ->
            if(userRecipes.any { userRecipe ->  userRecipe.id == recipe.id })
                recipe.liked = true
        }
        return recipes
    }
}