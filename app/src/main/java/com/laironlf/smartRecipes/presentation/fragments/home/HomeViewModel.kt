package com.laironlf.smartRecipes.presentation.fragments.home

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laironlf.smartRecipes.domain.models.params.GetRecipesListParams
import com.laironlf.smartRecipes.domain.models.Recipe
import com.laironlf.smartRecipes.domain.usecases.recipe.GetRecipeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRecipeListUseCase: GetRecipeListUseCase
) : ViewModel() {


    private val _newRecipesState = MutableLiveData<State>(State.Loading)
    val newRecipesState : LiveData<State> = _newRecipesState


    init {
        viewModelScope.launch {
            fetchRecipes()
        }
    }

    private suspend fun fetchRecipes() = try {
        _newRecipesState.value = State.Loading
        val recipes = getRecipeListUseCase(GetRecipesListParams())
        _newRecipesState.value = State.Loaded(recipes)
    } catch (e: Exception){
        Log.d(TAG, "fetchRecipes: ")
    }


    sealed interface State{
        data object Loading: State
        data class Loaded(
            val recipes: List<Recipe>
        ): State
        data object Error: State
    }

}