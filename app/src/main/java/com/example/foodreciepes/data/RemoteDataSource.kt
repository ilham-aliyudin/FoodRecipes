package com.example.foodreciepes.data

import com.example.foodreciepes.data.network.FoodRecipesApi
import com.example.foodreciepes.models.FoodRecipe
import retrofit2.Response
import javax.inject.Inject


class RemoteDataSource @Inject constructor(
    private val foodRecipesApi: FoodRecipesApi,

    ) {
    suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipe> {
        return foodRecipesApi.getRecipes(queries)
    }
}