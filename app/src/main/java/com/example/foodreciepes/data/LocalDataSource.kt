package com.example.foodreciepes.data

import com.example.foodreciepes.data.database.dao.FavoritesDao
import com.example.foodreciepes.data.database.dao.RecipesDao
import com.example.foodreciepes.data.database.entities.FavoritesEntity
import com.example.foodreciepes.data.database.entities.RecipesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class LocalDataSource @Inject constructor(
    private val recipesDao: RecipesDao,
    private val favoritesDao: FavoritesDao
) {

    /**
     * Recipes Entity
     */
    fun readDatabase(): Flow<List<RecipesEntity>> {
        return recipesDao.readRecipes()
    }

    suspend fun insertRecipes(recipesEntity: RecipesEntity) {
        recipesDao.insertRecipes(recipesEntity)
    }

    /**
     * Favorite Entity
     */
    fun readFavoritesRecipes(): Flow<List<FavoritesEntity>> {
        return favoritesDao.readFavoritesRecipes()
    }

    suspend fun insertFavoritesRecipes(favoritesEntity: FavoritesEntity) {
        favoritesDao.insertTOoFavoriteRecipes(favoritesEntity)
    }

    suspend fun deleteAllFavoriteRecipes() {
        favoritesDao.deleteAllFavoriteRecipes()
    }

    suspend fun deleteFavoriteRecipes(favoritesEntity: FavoritesEntity) {
        favoritesDao.deleteFavoritesRecipes(favoritesEntity)
    }
}