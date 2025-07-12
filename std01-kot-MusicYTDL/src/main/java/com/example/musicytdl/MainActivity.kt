package com.example.musicytdl

import android.os.Bundle
import android.widget.Button
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


class MainActivity : ComponentActivity() {
    private lateinit var button1: Button
    private lateinit var button2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout1)



        button1 = findViewById(R.id.button1)


        button1.setOnClickListener {
            setContentView(R.layout.layout2)
//            button2 = findViewById(R.id.button2)
        }
//        button2.setOnClickListener {
//            setContentView(R.layout.layout1)
//        }

//        enableEdgeToEdge()
//        setContent {
//            MusicYTDLTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
//        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MusicYTDLTheme {
        Greeting("Android")
    }
}