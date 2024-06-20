package com.laironlf.smartRecipes.presentation.fragments.recipes

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laironlf.smartRecipes.domain.models.params.GetRecipesListParams
import com.laironlf.smartRecipes.domain.models.params.GetRecipesListParams.FetchType
import com.laironlf.smartRecipes.domain.models.Recipe
import com.laironlf.smartRecipes.domain.models.params.LikeRecipeParams
import com.laironlf.smartRecipes.domain.usecases.recipe.GetRecipeListUseCase
import com.laironlf.smartRecipes.domain.usecases.recipe.StorageRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val getRecipeListUseCase: GetRecipeListUseCase,
    private val recipeLikeUseCase: StorageRecipeUseCase
)  : ViewModel() {

    private val _state: MutableLiveData<State> = MutableLiveData(State.Loading)
    val state: LiveData<State> = _state

    val tabPos: MutableLiveData<Int> = MutableLiveData(0)

    init {
        viewModelScope.launch { fetchRecipes() }
    }

    fun onTabClicked(){
        viewModelScope.launch { fetchRecipes() }
    }

    fun onRecipeLikeClicked(recipe: Recipe){
        viewModelScope.launch { recipeLikeUseCase(LikeRecipeParams(recipe)) }
    }

    private suspend fun fetchRecipes() = try {
        val params = GetRecipesListParams(
            when(tabPos.value){
                1 -> FetchType.Now
                2 -> FetchType.Saved
                else -> FetchType.All
            }
        )
        Log.d(TAG, "fetchRecipes: ${tabPos.value}")
        _state.postValue(State.Loading)
        val list = getRecipeListUseCase(params)
        val sortedList = list.sortedBy { it.ingredients.size - it.matchesProducts.size }
        _state.postValue(State.Loaded(sortedList))
    } catch (e: Exception){
        Log.d(TAG, "fetchRecipes: ${e.message}")
        _state.postValue(State.Error(e.message.toString()))
    }

    sealed interface State {
        data object Loading : State
        data class Loaded(val recipes: List<Recipe>) : State
        data class Error(val error: String) : State
        data object Empty : State
    }

}