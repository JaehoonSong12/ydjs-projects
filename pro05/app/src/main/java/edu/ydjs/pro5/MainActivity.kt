package edu.ydjs.pro5

import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioButton: RadioButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        radioGroup = findViewById(R.id.radioGroup)

        radioGroup.setOnCheckedChangeListener { radioGroup, id ->
            radioButton = findViewById(id)

            when(radioButton.id) {
                R.id.radioButton_yes -> {
                    Toast.makeText(this@MainActivity, "Hell Yeah!", Toast.LENGTH_SHORT).show()
                }
                R.id.radioButton_no -> {
                    Toast.makeText(this@MainActivity, "No? Just why?", Toast.LENGTH_SHORT).show()
                }
                R.id.radioButton_help -> {
                    Toast.makeText(this@MainActivity, "Ur in my house.", Toast.LENGTH_SHORT).show()
                }
                R.id.radioButton_tf -> {
                    Toast.makeText(this@MainActivity, "U dumb...", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}