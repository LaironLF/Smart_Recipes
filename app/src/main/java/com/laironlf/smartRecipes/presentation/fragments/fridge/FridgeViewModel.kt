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
import com.laironlf.smartRecipes.domain.usecases.product.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FridgeViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _state: MutableLiveData<State> = MutableLiveData(State.Loading)
    val state: LiveData<State> = _state

    init {
        viewModelScope.launch { fetchProducts() }
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

    private suspend fun addUserProduct(product: Product) = try {
        productRepository.saveUserProduct(product)
    } catch (e: Exception){
        Log.d(TAG, "addUserProduct: ${e.message}")
    }

    private suspend fun deleteUserProduct(product: Product) = try {
        productRepository.deleteUserProduct(product)
    } catch (e:Exception) {
        Log.d(TAG, "deleteUserProduct: ${e.message}")
    }

    fun onProductClick(product: Product, position: Int) {
//        if (selectedTabPos == 1) viewModelScope.launch { addUserProduct(product) }
//        if (selectedTabPos == 0) viewModelScope.launch { deleteUserProduct(product) }
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