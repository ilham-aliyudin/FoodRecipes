package com.example.foodreciepes.util

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.foodreciepes.viewmodel.RecipesViewModel

class MyExtensionFunction {
    companion object {
        fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
            observe(lifecycleOwner, object : Observer<T> {
                override fun onChanged(t: T) {
                    removeObserver(this)
                    observer.onChanged(t)
                }
            })
        }

        fun showToast(context: Context, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT ).show()
        }
    }
}
