package com.laironlf.smartRecipes.presentation.fragments.recipedetailinfo

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laironlf.smartRecipes.domain.models.Recipe
import com.laironlf.smartRecipes.domain.models.RecipeStep
import com.laironlf.smartRecipes.domain.usecases.recipe.GetRecipeIngredientsUseCase
import com.laironlf.smartRecipes.domain.usecases.recipe.GetRecipeStepsUseCase
import com.laironlf.smartRecipes.domain.usecases.recipe.GetRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailInfoViewModel @Inject constructor(
    private val getRecipeIngredientsUseCase: GetRecipeIngredientsUseCase,
    private val getRecipeStepsUseCase: GetRecipeStepsUseCase,
    private val getRecipeUseCase: GetRecipeUseCase
) : ViewModel() {


    private val _state: MutableLiveData<State> = MutableLiveData(State.Loading)
    val state: LiveData<State> = _state

    private val _recipeStepState: MutableLiveData<RecipeStepState> = MutableLiveData(RecipeStepState.Loading)
    val recipeStepState: LiveData<RecipeStepState> = _recipeStepState



    var recipeId: Int? = null

    fun onRecipeDetailFragmentCreated() = viewModelScope.launch {
        fetchRecipe()
    }

    private suspend fun fetchRecipe() = try {
        _state.postValue(State.Loading)
        val recipe = getRecipeUseCase(recipeId!!)
        _state.postValue(State.Loaded(recipe))
        fetchRecipeStep()
    }
    catch (e: NullPointerException){
        _state.postValue(State.Error("Рецепт не найден"))
    }
    catch (e: Exception){
        Log.d(TAG, "fetchRecipe: ${e.message}")
    }

    private suspend fun fetchRecipeStep() = try {
        _recipeStepState.postValue(RecipeStepState.Loading)
        val recipeStepList = getRecipeStepsUseCase(recipeId!!)
        _recipeStepState.postValue(RecipeStepState.Loaded(recipeStepList))
    } catch (e: Exception){

    }

    sealed interface State {
        data object Loading: State
        data class Error (
            val msg: String,
        ): State
        data class Loaded (
            val recipe: Recipe,
        ): State
    }
    sealed interface RecipeStepState {
        data object Loading: RecipeStepState
        data object Error: RecipeStepState
        data object Empty: RecipeStepState
        data class Loaded(val recipeStepList: List<RecipeStep>): RecipeStepState
    }

}
