package com.example.musicytdl

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.musicytdl.ui.theme.MusicYTDLTheme

// https://www.youtube.com/watch?v=blKkRoZPxLc
// https://www.youtube.com/watch?v=BBWyXo-3JGQ&t=2122s

class MainActivity : FragmentActivity(), BaseSceneFragment.SceneNavigationListener {
    private var selectedScene: Int = -1
    private lateinit var sceneAdapter: SceneAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // requires android:fitsSystemWindows="true" on each XML layout
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





// class MainActivity : ComponentActivity() {
//     private lateinit var button1: Button
//     private lateinit var button2: Button

//     override fun onCreate(savedInstanceState: Bundle?) {
//         super.onCreate(savedInstanceState)
//         setContentView(R.layout.layout1)



//         button1 = findViewById(R.id.button1)


//         button1.setOnClickListener {
//             setContentView(R.layout.layout2)
// //            button2 = findViewById(R.id.button2)
//         }
// //        button2.setOnClickListener {
// //            setContentView(R.layout.layout1)
// //        }

// //        enableEdgeToEdge()
// //        setContent {
// //            MusicYTDLTheme {
// //                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
// //                    Greeting(
// //                        name = "Android",
// //                        modifier = Modifier.padding(innerPadding)
// //                    )
// //                }
// //            }
// //        }
//     }
// }

// @Composable
// fun Greeting(name: String, modifier: Modifier = Modifier) {
//     Text(
//         text = "Hello $name!",
//         modifier = modifier
//     )
// }

// @Preview(showBackground = true)
// @Composable
// fun GreetingPreview() {
//     MusicYTDLTheme {
//         Greeting("Android")
//     }
// }