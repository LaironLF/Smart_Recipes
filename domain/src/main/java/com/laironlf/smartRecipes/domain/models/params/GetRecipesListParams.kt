package com.laironlf.smartRecipes.domain.models.params

data class GetRecipesListParams (
    val fetchType: FetchType = FetchType.All,
    var productsId: List<Int>? = null
) {
    sealed interface FetchType {
        data object All: FetchType
        data object Saved: FetchType
        data object Now : FetchType
    }
}