package com.laironlf.smartRecipes.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.laironlf.smartRecipes.data.room.entity.MeasureTypeDB
import com.laironlf.smartRecipes.data.room.entity.ProductDB
import com.laironlf.smartRecipes.data.room.entity.RecipeDB
import com.laironlf.smartRecipes.data.room.entity.RecipeIngredientDB
import com.laironlf.smartRecipes.data.room.entity.RecipeStepDB
import com.laironlf.smartRecipes.data.room.entity.UserProductDB
import com.laironlf.smartRecipes.data.room.entity.UserRecipeDB

@Database(version = 4, exportSchema = false, entities = [
    RecipeDB::class,
    ProductDB::class,
    MeasureTypeDB::class,
    RecipeIngredientDB::class,
    RecipeStepDB::class,
    UserProductDB::class,
    UserRecipeDB::class
])
abstract class AppRecipesDatabase: RoomDatabase() {
    abstract fun getRoomDBDao(): RoomDBDao
    companion object {
        private const val dataBaseName = "AppRecipesDatabase"
        private var INSTANCE: AppRecipesDatabase? = null

        fun getDataBase(context: Context): AppRecipesDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppRecipesDatabase::class.java,
                    dataBaseName
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}