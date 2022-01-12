package com.example.foodreciepes.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import com.example.foodreciepes.R
import com.example.foodreciepes.adapters.PagerAdapter
import com.example.foodreciepes.data.database.entities.FavoritesEntity
import com.example.foodreciepes.ui.fragments.ingredients.IngredientsFragment
import com.example.foodreciepes.ui.fragments.instruction.InstructionFragment
import com.example.foodreciepes.ui.fragments.overview.OverviewFragment
import com.example.foodreciepes.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_details.*

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private val args by navArgs<DetailsActivityArgs>()
    private val mainViewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val fragments = ArrayList<Fragment>()
        fragments.add(OverviewFragment())
        fragments.add(IngredientsFragment())
        fragments.add(InstructionFragment())

        val title = ArrayList<String>()
        title.add("Overview")
        title.add("Ingredients")
        title.add("Instructions")

        val resultBundle = Bundle()
        resultBundle.putParcelable("recipesBundle", args.result)

        val adapter = PagerAdapter(
            resultBundle,
            fragments,
            title,
            supportFragmentManager
        )

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_menu, menu)
        val menuItem = menu?.findItem(R.id.favorite_menu)
        checkSavedRecipes(menuItem!!)
        return true
    }

    private fun checkSavedRecipes(menu: MenuItem) {
        mainViewModel.readFavoritesRecipes.observe(this, { favoritesEntity ->
            try {
                for (savedRecipes in favoritesEntity) {
                    if (savedRecipes.result.id == args.result.id) {
                        changeColorIcon(menu, R.color.yellow)
                    }
                }
            } catch (e: Exception) {
            }
        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite_menu -> {
                saveToFavorites(item)
            }
            R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveToFavorites(item: MenuItem) {
        val favoritesEntity = FavoritesEntity(
            0,
            args.result
        )
        mainViewModel.insertFavoritesRecipes(favoritesEntity)
        changeColorIcon(item, R.color.yellow)
        Snackbar.make(layoutdetails, "Succesfully saved", Snackbar.LENGTH_SHORT).setAction(
            "saved", null
        ).show()
    }

    private fun changeColorIcon(item: MenuItem, color: Int) {
        item.icon.setTint(ContextCompat.getColor(this, color))
    }
}