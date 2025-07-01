package com.example.musicytdl

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit

// https://www.youtube.com/watch?v=blKkRoZPxLc
// https://www.youtube.com/watch?v=BBWyXo-3JGQ&t=2122s

class MainActivity : FragmentActivity(), BaseSceneFragment.SceneNavigationListener {
    private var selectedScene: Int = -1
    private lateinit var sceneAdapter: SceneAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val menuContainer = findViewById<View>(R.id.menu_container)
        val fragmentContainer = findViewById<View>(R.id.fragment_container)
        val sceneRecyclerView = findViewById<RecyclerView>(R.id.scene_recycler_view)
        val startButton = findViewById<Button>(R.id.start_button)
        val exitButton = findViewById<Button>(R.id.exit_button)

        val scenes = listOf("Scene 1", "Scene 2")
        sceneAdapter = SceneAdapter(scenes) { position ->
            selectedScene = position
        }
        sceneRecyclerView.layoutManager = LinearLayoutManager(this)
        sceneRecyclerView.adapter = sceneAdapter

        startButton.setOnClickListener {
            val fragment: Fragment = when (selectedScene) {
                0 -> SceneOneFragment()
                1 -> SceneTwoFragment()
                else -> return@setOnClickListener // Do nothing if nothing selected
            }
            menuContainer.visibility = View.GONE
            fragmentContainer.visibility = View.VISIBLE
            supportFragmentManager.commit {
                replace(R.id.fragment_container, fragment)
                setReorderingAllowed(true)
                addToBackStack(null)
            }
        }
        exitButton.setOnClickListener {
            finish()
        }
        // Show menu on launch
        menuContainer.visibility = View.VISIBLE
        fragmentContainer.visibility = View.GONE
    }

    override fun onBackToMenu() {
        val menuContainer = findViewById<View>(R.id.menu_container)
        val fragmentContainer = findViewById<View>(R.id.fragment_container)
        supportFragmentManager.popBackStack()
        menuContainer.visibility = View.VISIBLE
        fragmentContainer.visibility = View.GONE
    }
}