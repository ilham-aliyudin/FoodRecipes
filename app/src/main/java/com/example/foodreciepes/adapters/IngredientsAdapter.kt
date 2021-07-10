package com.example.foodreciepes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.foodreciepes.R
import com.example.foodreciepes.models.ExtendedIngredient
import com.example.foodreciepes.util.Constant.BASE_IMAGE_URL
import com.example.foodreciepes.util.RecipesDiffUtil
import kotlinx.android.synthetic.main.ingredient_row_layout.view.*

class IngredientsAdapter : RecyclerView.Adapter<IngredientsAdapter.MyViewHolder>() {

    private var ingredientsList = emptyList<ExtendedIngredient>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.ingredient_row_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.iv_ingredient.load(BASE_IMAGE_URL + ingredientsList[position].image)
        holder.itemView.tv_ingredient_name.text = ingredientsList[position].name
        holder.itemView.tv_ingredient_amount.text = ingredientsList[position].amount.toString()
        holder.itemView.tv_ingredient_consistency.text = ingredientsList[position].consistency
        holder.itemView.tv_ingredient_unit.text = ingredientsList[position].unit.toString()
        holder.itemView.tv_ingredient_original.text = ingredientsList[position].original
    }

    override fun getItemCount(): Int {
        return ingredientsList.size
    }

    fun setData(newIngredientList: List<ExtendedIngredient>) {
        val ingredientsDiffUtil = RecipesDiffUtil(ingredientsList, newIngredientList)
        val diffUtilResult = DiffUtil.calculateDiff(ingredientsDiffUtil)
        ingredientsList = newIngredientList
        diffUtilResult.dispatchUpdatesTo(this)
    }
}