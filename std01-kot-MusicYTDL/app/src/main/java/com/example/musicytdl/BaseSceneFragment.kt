package com.example.musicytdl

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.fragment.app.Fragment

abstract class BaseSceneFragment(layoutRes: Int) : Fragment(layoutRes) {
    interface SceneNavigationListener {
        fun onBackToMenu()
    }
    private var navigationListener: SceneNavigationListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SceneNavigationListener) {
            navigationListener = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<View>(R.id.button_back_to_menu)?.setOnClickListener {
            navigationListener?.onBackToMenu()
        }
    }
} 