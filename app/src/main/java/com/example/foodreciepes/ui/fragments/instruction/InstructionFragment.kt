package com.example.foodreciepes.ui.fragments.instruction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.example.foodreciepes.R
import com.example.foodreciepes.models.Result
import com.example.foodreciepes.util.Constant.PARCELIZE_KEY
import kotlinx.android.synthetic.main.fragment_instruction.view.*


class InstructionFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val mView = inflater.inflate(R.layout.fragment_instruction, container, false)
        val args = arguments
        val myBundle: Result? = args?.getParcelable(PARCELIZE_KEY)


        mView.apply {
            wv_instructions.webViewClient = object : WebViewClient() {}
            val websiteUrl: String? = myBundle!!.sourceUrl
            websiteUrl?.let { wv_instructions.loadUrl(websiteUrl) }
        }

        return mView
    }
}