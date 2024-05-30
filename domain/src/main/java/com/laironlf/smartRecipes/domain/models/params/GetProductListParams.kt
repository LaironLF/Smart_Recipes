package com.laironlf.smartRecipes.domain.models.params

data class GetProductListParams (
    val fetchType: FetchType,
    val searchString: String? = null
) {
    sealed interface FetchType{
        data object UserProducts: FetchType
        data object AllRemoteProducts: FetchType
        data object AllRemoteProductsExceptUserProducts: FetchType
    }
}
