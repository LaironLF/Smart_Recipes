package com.laironlf.smartRecipes.domain.usecases

import com.laironlf.smartRecipes.domain.models.params.GetProductListParams
import com.laironlf.smartRecipes.domain.models.Recipe
import com.laironlf.smartRecipes.domain.repository.ProductRepository
import com.laironlf.smartRecipes.domain.repository.RecipeRepository

class GetRecipeUseCase (
    private val recipeRepository: RecipeRepository,
    private val productRepository: ProductRepository
){
    suspend operator fun invoke(id: Int): Recipe{
        val recipe = recipeRepository.getRecipeById(id)!!
        val products = productRepository.getProductList(GetProductListParams(fetchUserProducts = true))
        recipe.matchesProducts = products.filter { product ->
            recipe.ingredients.any { ingredient ->
                ingredient.product.id == product.id
            }
        }
        return recipe
    }
}