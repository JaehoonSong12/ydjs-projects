package edu.ydjs.pro4

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var myButton: FloatingActionButton
    private lateinit var myView: View
    private val colors = arrayOf(
        Color.BLACK,
        Color.DKGRAY,
        Color.GRAY,
        Color.LTGRAY,
        Color.WHITE,
        Color.RED,
        Color.GREEN,
        Color.BLUE,
        Color.YELLOW,
        Color.CYAN,
        Color.MAGENTA,
        Color.TRANSPARENT
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        myButton = findViewById(R.id.color_button)
        myView = findViewById(R.id.main)

        myButton.setOnClickListener {
            myView.setBackgroundColor(colors[Random.nextInt(colors.size)])
        }
    }
}