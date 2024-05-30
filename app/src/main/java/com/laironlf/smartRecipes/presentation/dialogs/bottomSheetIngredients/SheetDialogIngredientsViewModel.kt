package com.laironlf.smartRecipes.presentation.dialogs.bottomSheetIngredients

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laironlf.smartRecipes.domain.models.MeasureType
import com.laironlf.smartRecipes.domain.models.Product
import com.laironlf.smartRecipes.domain.models.params.GetProductListParams
import com.laironlf.smartRecipes.domain.models.post.ProductPost
import com.laironlf.smartRecipes.domain.usecases.product.GetMeasureTypesUseCase
import com.laironlf.smartRecipes.domain.usecases.product.GetProductsUseCase
import com.laironlf.smartRecipes.domain.usecases.product.UploadNewProductUseCase
import com.laironlf.smartRecipes.presentation.dialogs.bottomSheetIngredients.SheetDialogIngredients.Companion.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SheetDialogIngredientsViewModel @Inject constructor(
    val getProductsUseCase: GetProductsUseCase,
    val getMeasureTypesUseCase: GetMeasureTypesUseCase,
    val uploadProductUseCase: UploadNewProductUseCase
): ViewModel() {
    val queryText: MutableLiveData<String> = MutableLiveData("")
    val newProductTitle: MutableLiveData<String> = MutableLiveData("")
    var measureTypes: MutableLiveData<List<MeasureType>> = MutableLiveData()

    private val _state: MutableLiveData<State> = MutableLiveData(State.Idle)
    val state: LiveData<State> = _state
    init {

        viewModelScope.launch {
            measureTypes.postValue(getMeasureTypesUseCase())
        }

    }

    fun productSelected(){
        _state.postValue(State.Selected)
    }

    fun startFetching(query: String) {
        viewModelScope.launch {
            fetchProducts(query)
        }
    }
    private suspend fun fetchProducts(query: String) {
        Log.d(TAG, "fetchProducts: $query")
        if (query.isEmpty()){
            _state.postValue(State.Idle)
            return
        }
        _state.postValue(State.Loading)
        val params = GetProductListParams(GetProductListParams.FetchType.AllRemoteProducts, query)
        try {
            val products = getProductsUseCase(params)
            if (products.isEmpty()) _state.postValue(State.Empty)
            else _state.postValue(State.Loaded(products))
        } catch (e: Exception) {
            e.printStackTrace()
            _state.postValue(State.Error(e.message ?: "Ошибка во время поиска"))
        }
    }

    fun onAddNewProductOfferClicked(){
        _state.postValue(State.AddProduct)
        newProductTitle.postValue(queryText.value)
    }

    fun onAddNewProductClicked() = viewModelScope.launch {
        uploadNewProduct()
    }

    private suspend fun uploadNewProduct() = try {
        _state.postValue(State.Loading)
        val product = ProductPost(
            idProductType = 1,
            title = newProductTitle.value
        )
        uploadProductUseCase(product)
        _state.postValue(State.Idle)
        queryText.postValue(newProductTitle.value)
    } catch (e: Exception){
        e.printStackTrace()
    }

    fun onClearAction() {
        queryText.postValue("")
        _state.postValue(State.Empty)
    }


    sealed interface State {
        data object Idle: State
        data object Loading : State
        data class Loaded(val products: List<Product>) : State
        data object Selected: State
        data class Error(val error: String) : State
        data object Empty : State
        data object AddProduct: State
    }


}