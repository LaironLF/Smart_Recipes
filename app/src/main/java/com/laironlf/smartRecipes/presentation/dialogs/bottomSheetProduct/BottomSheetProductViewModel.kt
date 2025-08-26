package com.laironlf.smartRecipes.presentation.dialogs.bottomSheetProduct

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.laironlf.smartRecipes.domain.models.Product
import com.laironlf.smartRecipes.domain.models.params.GetProductListParams
import com.laironlf.smartRecipes.domain.usecases.product.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BottomSheetProductViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
): ViewModel() {

    private val _state: MutableLiveData<State> = MutableLiveData(State.Idle)
    val state: LiveData<State> = _state

    val newProductTitle: MutableLiveData<String> = MutableLiveData("")
    val query: MutableLiveData<String> = MutableLiveData("")

    init {
        viewModelScope.launch {
            query.asFlow().collect{ fetchProducts(it) }
        }
    }

    private suspend fun fetchProducts(query: String?) = try {
        _state.postValue(State.Loading)
        val params = GetProductListParams(
            fetchType = GetProductListParams.FetchType.AllRemoteProducts,
            searchString = query
        )
        val list = getProductsUseCase(params).sortedBy { it -> it.title }
        if (list.isEmpty()) _state.postValue(State.Empty)
        else _state.postValue(State.Loaded(list))
    } catch (e: Exception){
        e.printStackTrace()
    }

    sealed interface State {
        data object Idle: State
        data object Loading: State
        data class Loaded(val products: List<Product>): State
        data object Empty: State
    }
}