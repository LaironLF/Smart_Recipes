package com.laironlf.smartRecipes.data.room

import androidx.room.Dao
import androidx.room.Index
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.laironlf.smartRecipes.data.room.entity.MeasureTypeDB
import com.laironlf.smartRecipes.data.room.entity.ProductDB
import com.laironlf.smartRecipes.data.room.entity.RecipeDB
import com.laironlf.smartRecipes.data.room.entity.RecipeIngredientDB
import com.laironlf.smartRecipes.data.room.entity.RecipeStepDB
import com.laironlf.smartRecipes.data.room.entity.UserProductDB
import com.laironlf.smartRecipes.data.room.entity.UserRecipeDB


@Dao
interface RoomDBDao {

    // recipes

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRecipe(recipeDB: RecipeDB)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRecipeToUserList(userRecipeDB: UserRecipeDB)

    @Query("Select * FROM RecipeDB")
    suspend fun getRecipes(): List<RecipeDB>

    @Query("Select * FROM RecipeDB Where RecipeDB.id = :id")
    suspend fun getRecipeById(id: Int): RecipeDB

    @Query("Select * FROM RecipeStepDB Where RecipeStepDB.id_Recipe = :recipeId")
    suspend fun getRecipeSteps(recipeId: Int): List<RecipeStepDB>

    @Query("Select * FROM RecipeIngredientDB where RecipeIngredientDB.id_Recipe = :recipeId")
    suspend fun getRecipeIngredients(recipeId: Int) : List<RecipeIngredientDB>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRecipeIngredients(ingredients: List<RecipeIngredientDB>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRecipeSteps(recipeSteps: List<RecipeStepDB>)

    // products

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProductToUserList(userProductDB: UserProductDB)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProduct(productDB: ProductDB)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProductList(productList: List<ProductDB>)

    @Query("DELETE FROM ProductDB WHERE 1")
    suspend fun clearProductList()

    @Query("DELETE FROM UserProductDB WHERE UserProductDB.id_Product = :id")
    suspend fun deleteProductFromUserList(id: Int)

    @Query("SELECT ProductDB.id, ProductDB.title FROM UserProductDB INNER JOIN ProductDB on ProductDB.id = UserProductDB.id_Product")
    suspend fun getUserProducts(): List<ProductDB>

    @Query("SELECT ProductDB.id, ProductDB.title FROM UserProductDB " +
            "INNER JOIN ProductDB on ProductDB.id = UserProductDB.id_Product " +
            "Where ProductDB.id = :id")
    suspend fun getUserProductById(id :Int): ProductDB?

    @Query("Select * From ProductDB where ProductDB.id = :id")
    suspend fun getProductById(id: Int): ProductDB?

    @Query("Select * From ProductDB")
    suspend fun getProductList(): List<ProductDB>

    @Query("Select * From MeasureTypeDB where MeasureTypeDB.id = :id")
    suspend fun getMeasureTypeById(id: Int): MeasureTypeDB

}