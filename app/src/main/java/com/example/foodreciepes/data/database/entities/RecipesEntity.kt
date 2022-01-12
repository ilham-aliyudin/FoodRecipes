package com.example.foodreciepes.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodreciepes.models.FoodRecipe
import com.example.foodreciepes.util.Constant.RECIPES_TABLE


@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(
    var foodRecipe: FoodRecipe
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}