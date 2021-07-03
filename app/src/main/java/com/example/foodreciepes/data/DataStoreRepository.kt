package com.example.foodreciepes.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.foodreciepes.util.Constant
import com.example.foodreciepes.util.Constant.PREFERENCES_DIET_TYPE
import com.example.foodreciepes.util.Constant.PREFERENCES_DIET_TYPE_ID
import com.example.foodreciepes.util.Constant.PREFERENCES_MEAL_TYPE
import com.example.foodreciepes.util.Constant.PREFERENCES_MEAL_TYPE_ID
import com.example.foodreciepes.util.Constant.PREFERENCES_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class DataStoreRepository @Inject constructor(
    @ApplicationContext private var context: Context
) {
    private object PreferenceKeys {
        val selectedMealType = stringPreferencesKey(PREFERENCES_MEAL_TYPE)
        val selectedMealTypeId = intPreferencesKey(PREFERENCES_MEAL_TYPE_ID)
        val selectedDietType = stringPreferencesKey(PREFERENCES_DIET_TYPE)
        val selectedDietTypeId = intPreferencesKey(PREFERENCES_DIET_TYPE_ID)
    }

    private val Context._dataStore: DataStore<Preferences> by preferencesDataStore(PREFERENCES_NAME)
    private val dataStore: DataStore<Preferences> = context._dataStore

    suspend fun saveMealAndDietType(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    ) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.selectedMealType] = mealType
            preferences[PreferenceKeys.selectedMealTypeId] = mealTypeId
            preferences[PreferenceKeys.selectedDietType] = dietType
            preferences[PreferenceKeys.selectedDietTypeId] = dietTypeId
        }
    }
}

data class MealAndDietType(
    val selectedMealType: String,
    val selectedMealTypeId: Int,
    val selectedDietType: String,
    val selectedDietTypeId: Int
)