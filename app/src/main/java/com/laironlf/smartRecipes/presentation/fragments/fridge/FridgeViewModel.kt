package com.laironlf.smartRecipes.presentation.fragments.fridge

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laironlf.smartRecipes.domain.models.params.GetProductListParams
import com.laironlf.smartRecipes.domain.models.Product
import com.laironlf.smartRecipes.domain.models.params.GetProductListParams.FetchType
import com.laironlf.smartRecipes.domain.repository.ProductRepository
import com.laironlf.smartRecipes.domain.usecases.product.AddProductUserUseCase
import com.laironlf.smartRecipes.domain.usecases.product.AddProductUserUseCase.ProductAlreadyIsException
import com.laironlf.smartRecipes.domain.usecases.product.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FridgeViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val productRepository: ProductRepository,
    private val addProductUserUseCase: AddProductUserUseCase
) : ViewModel() {

    private val _state: MutableLiveData<State> = MutableLiveData(State.Loading)
    val state: LiveData<State> = _state

    private val _actions: Channel<Action> = Channel(Channel.BUFFERED)
    val actions: Flow<Action> = _actions.receiveAsFlow()

    init {
        viewModelScope.launch { fetchUserProducts() }
    }

    private suspend fun fetchUserProducts() = try {
        _state.postValue(State.Loading)
        val params = GetProductListParams(
            fetchType = FetchType.UserProducts
        )
        val list = getProductsUseCase(params)
        _state.postValue(State.Loaded(list))
    } catch (e: Exception){
        e.printStackTrace()
    }

    private suspend fun fetchProducts() = try {
//        val fetchType = FetchType.AllRemoteProductsExceptUserProducts
//
//        val getProductListParams = GetProductListParams(fetchType = fetchType)
//        _state.postValue(State.Loading)
//        getProductsUseCase(getProductListParams).apply {
//            _state.postValue(State.Loaded(this))
//        }

    } catch (e: Exception) {
        _state.postValue(State.Error)
        Log.d(TAG, "fetchProducts: ${e.message}")
    }


    fun onProductClick(product: Product, position: Int) {
        viewModelScope.launch { productRepository.deleteUserProduct(product) }
    }

    fun onAddProduct(product: Product) = viewModelScope.launch {
        try {
            addProductUserUseCase(product)
            fetchUserProducts()
        } catch (e: ProductAlreadyIsException){
            viewModelScope.launch { _actions.send(Action.ShowToast("Продукт уже добавлен")) }
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    sealed interface Action {
        data class ShowToast(val message: String): Action
    }

    sealed interface State {
        data object Loading: State
        data object Empty: State
        data object Error: State
        data class Loaded(
            val products: List<Product>
        ) : State
    }

}