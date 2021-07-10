package com.example.foodreciepes.ui.fragments.ingredients

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodreciepes.R
import com.example.foodreciepes.adapters.IngredientsAdapter
import com.example.foodreciepes.models.Result
import com.example.foodreciepes.ui.DetailsActivityArgs
import com.example.foodreciepes.util.Constant.PARCELIZE_KEY
import kotlinx.android.synthetic.main.fragment_ingredients.*
import kotlinx.android.synthetic.main.fragment_ingredients.view.*


class IngredientsFragment : Fragment() {

    private val mAdapter: IngredientsAdapter by lazy { IngredientsAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val mView = inflater.inflate(R.layout.fragment_ingredients, container, false)
        val args = arguments
        val myBundle: Result? = args?.getParcelable(PARCELIZE_KEY)

        setRecyclerView(mView)
        myBundle?.extendedIngredients?.let {
            mAdapter.setData(it)
        }
        return mView
    }

    private fun setRecyclerView(view: View) {
        view.rv_ingredient.adapter = mAdapter
        view.rv_ingredient.layoutManager = LinearLayoutManager(requireContext())
    }
}