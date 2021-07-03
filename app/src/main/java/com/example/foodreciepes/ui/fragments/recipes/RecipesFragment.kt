package com.example.foodreciepes.ui.fragments.recipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodreciepes.viewmodel.MainViewModel
import com.example.foodreciepes.R
import com.example.foodreciepes.viewmodel.RecipesViewModel
import com.example.foodreciepes.adapters.RecipesAdapter
import com.example.foodreciepes.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_recipes.view.*

@AndroidEntryPoint
class RecipesFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var recipeViewModel: RecipesViewModel
    private lateinit var mView: View
    private val mAdapter by lazy { RecipesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        recipeViewModel = ViewModelProvider(requireActivity())[RecipesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_recipes, container, false)

        setUpRecyclerView()
        readDatabase()
        return mView
    }

    private fun readDatabase() {
        mainViewModel.readRecipes.observe(viewLifecycleOwner,  { database ->
            if(database.isNotEmpty()) {
                mAdapter.setData(database[0].foodRecipe)
                hideShimmerEffect()
            } else {
                requestApiData()
            }
        })
    }

    private fun requestApiData() {
        mainViewModel.getRecipes(recipeViewModel.applyQueries())
        mainViewModel.recipesResponse.observe(viewLifecycleOwner, { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideShimmerEffect()
                    response.data?.let { mAdapter.setData(it) }
                }
                is NetworkResult.Error -> {
                    hideShimmerEffect()
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is NetworkResult.Loading -> {
                    showShimmerEffect()
                }
            }
        })
    }

    private fun setUpRecyclerView() {
        mView.rv_shimmer.adapter = mAdapter
        mView.rv_shimmer.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect()
    }

    private fun showShimmerEffect() {
        mView.rv_shimmer.showShimmer()
    }

    private fun hideShimmerEffect() {
        mView.rv_shimmer.hideShimmer()
    }
}