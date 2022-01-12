package com.example.foodreciepes.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodreciepes.models.Result
import com.example.foodreciepes.util.Constant
import com.example.foodreciepes.util.Constant.FAVORITES_TABLE

@Entity(tableName = FAVORITES_TABLE)
data class FavoritesEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val result: Result
)