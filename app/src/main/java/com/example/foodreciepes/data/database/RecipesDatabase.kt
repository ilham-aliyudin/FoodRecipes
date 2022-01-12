package com.example.foodreciepes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodreciepes.data.database.dao.FavoritesDao
import com.example.foodreciepes.data.database.dao.RecipesDao
import com.example.foodreciepes.data.database.entities.FavoritesEntity
import com.example.foodreciepes.data.database.entities.RecipesEntity


@Database(
    entities = [RecipesEntity::class, FavoritesEntity::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(RecipesTypeConverter::class)
abstract class RecipesDatabase: RoomDatabase() {

    abstract fun recipesDao(): RecipesDao
    abstract fun favoritesDao(): FavoritesDao

}