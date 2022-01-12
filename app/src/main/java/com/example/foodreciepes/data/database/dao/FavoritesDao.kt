package com.example.foodreciepes.data.database.dao

import androidx.room.*
import com.example.foodreciepes.data.database.entities.FavoritesEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTOoFavoriteRecipes(
        favoritesEntity: FavoritesEntity
    )

    @Query("select * from FAVORITES_TABLE order by id asc")
    fun readFavoritesRecipes(): Flow<List<FavoritesEntity>>

    @Delete
    suspend fun deleteFavoritesRecipes(favoritesEntity: FavoritesEntity)

    @Query("delete from FAVORITES_TABLE")
    suspend fun deleteAllFavoriteRecipes()
}