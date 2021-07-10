package com.example.foodreciepes.ui.fragments.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import coil.load
import com.example.foodreciepes.R
import com.example.foodreciepes.models.Result
import kotlinx.android.synthetic.main.fragment_overview.view.*
import org.jsoup.Jsoup


class OverviewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val mvView = inflater.inflate(R.layout.fragment_overview, container, false)

        val args = arguments
        val myBundle: Result? = args?.getParcelable("recipesBundle")

        mvView.apply {
            main_imageview.load(myBundle?.image)
            title_textview.text = myBundle?.title
            likes_textview.text = myBundle?.aggregateLikes.toString()
            time_textview.text = myBundle?.readyInMinutes.toString()
            myBundle?.summary.let {
                val summary = Jsoup.parse(it).text()
                summary_textview.text = summary
            }

            if (myBundle?.vegetarian == true) {
                vegetarian_imageview.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGreen
                    )
                )
                vegetarian_textview.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGreen
                    )
                )
            }

            if (myBundle?.vegan == true) {
                vegan_imageview.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGreen
                    )
                )
                vegan_textview.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGreen
                    )
                )
            }

            if (myBundle?.glutenFree == true) {
                gluten_free_imageview.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGreen
                    )
                )
                gluten_textview.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGreen
                    )
                )
            }

            if (myBundle?.cheap == true) {
                cheap_imageview.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGreen
                    )
                )
                cheap_textview.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGreen
                    )
                )
            }

            if (myBundle?.dairyFree == true) {
                dairy_free_imageview.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGreen
                    )
                )

                dairy_free_textview.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGreen
                    )
                )
            }

            if (myBundle?.veryHealthy == true) {
                healthy_imageview.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGreen
                    )
                )

                healty_textview.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGreen
                    )
                )
            }
        }
        return mvView
    }
}