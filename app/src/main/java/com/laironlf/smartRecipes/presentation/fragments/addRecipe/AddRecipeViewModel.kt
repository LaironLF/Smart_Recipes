package com.laironlf.smartRecipes.presentation.fragments.addRecipe

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laironlf.smartRecipes.domain.models.post.IngredientPost
import com.laironlf.smartRecipes.domain.models.post.RecipePost
import com.laironlf.smartRecipes.domain.models.post.StepPost
import com.laironlf.smartRecipes.domain.usecases.recipe.AddRecipeUseCase
import com.laironlf.smartRecipes.domain.usecases.technical.UploadImageUseCase
import com.laironlf.smartRecipes.presentation.dialogs.bottomSheetIngredients.SheetDialogIngredients.Companion.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class AddRecipeViewModel @Inject constructor(
    private val addRecipeUseCase: AddRecipeUseCase,
    private val uploadImageUseCase: UploadImageUseCase
) : ViewModel() {
    private val _ingredients: MutableLiveData<List<IngredientPost>> = MutableLiveData(emptyList())
    val ingredients: LiveData<List<IngredientPost>> = _ingredients

    private val _steps: MutableLiveData<List<StepPost>> = MutableLiveData(emptyList())
    val steps: LiveData<List<StepPost>> = _steps

    private val _state: MutableLiveData<State> = MutableLiveData(State.Idle)
    val state: LiveData<State> = _state

    val imageUri: MutableLiveData<Uri> = MutableLiveData()
    val recipeTitle: MutableLiveData<String> = MutableLiveData("")
    val kCal: MutableLiveData<String> = MutableLiveData("")
    val hours: MutableLiveData<Int> = MutableLiveData(0)
    val minutes: MutableLiveData<Int> = MutableLiveData(0)

    private val _actions: Channel<Action> = Channel(Channel.BUFFERED)
    val actions: Flow<Action> = _actions.receiveAsFlow()

    fun onAddIngredientAction(ingredient: IngredientPost){
        val list = _ingredients.value?.toMutableList()
        if (list != null){
            list.add(ingredient)
            Log.d(TAG, "onAddIngredientAction: ${ingredient.product.title}")
            _ingredients.postValue(list.toList())
        }
    }

    fun onDeleteIngredientAction(ingredient: IngredientPost){
        val list = _ingredients.value?.toMutableList()
        if (list != null){
            list.remove(ingredient)
            _ingredients.postValue(list.toList())
        }
    }
    fun onAddStepAction(step: StepPost){
        val list = _steps.value?.toMutableList()
        if (list != null){
            list.add(step)
            _steps.postValue(list.toList())
        }
        list?.forEach {
            Log.d(TAG, "onAddStepAction: ${it.text}")
        }
    }

    fun onAddRecipeAction() = viewModelScope.launch {
        postRecipe()
    }
    private suspend fun postRecipe() = try {
        if (imageUri.value == null)
            throw IllegalArgumentException("Рецепт без главной фотографии \n(´･ᴗ･ ` )")
        if (recipeTitle.value == "")
            throw IllegalArgumentException("Пустое название рецепта")
        if (hours.value == 0 && minutes.value == 0)
            throw IllegalArgumentException("Рецепт который 0 по времени?\n＼(º □ º l|l)/")
        if (_steps.value!!.isEmpty())
            throw IllegalArgumentException("Рецепт без шагов приготовления!")
        if (_ingredients.value!!.isEmpty())
            throw IllegalArgumentException("Рецепт без игридиентов \n:O")

        _state.postValue(State.Loading("Оцениваем фоточки..."))
        val mainImageUrl = uploadImageUseCase(File(imageUri.value!!.path!!))
        val stepPost = postStepImages()
        val recipePost = RecipePost(
            calories = kCal.value!!.toInt(),
            cookingTime = String.format("%02d:%02d:00", hours.value, minutes.value),
            creatorUserId = 1,
            imageUrl = mainImageUrl,
            ingredients = _ingredients.value,
            steps = stepPost,
            title = recipeTitle.value
        )
        _state.postValue(State.Loading("Всматриваемся в суть рецепта..."))
        addRecipeUseCase(recipePost)
//        _actions.send(Action.Close)
        _state.postValue(State.Loaded)
    } catch (e: IllegalArgumentException){
        _actions.send(Action.ShowToastAction(e.message?: ""))
        e.printStackTrace()
    } catch (e: Exception){
        e.printStackTrace()
    }

    private suspend fun postStepImages(): List<StepPost> {
        val stepsWithUrl: MutableList<StepPost> = mutableListOf()
        for(step in _steps.value!!){
            var url: String? = null
            if (step.imageUrl != "") url = uploadImageUseCase(File(step.imageUrl))
            stepsWithUrl.add(StepPost(url, step.text))
        }
        return stepsWithUrl
    }

    sealed interface State {
        object Idle : State
        data class Loading(val message: String): State
        data class Error(val message: String): State
        object Loaded: State
    }
    sealed interface Action {
        data class ShowToastAction(val message: String) : Action
        object Close : Action
    }

}