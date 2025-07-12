package edu.ydjs.pro3

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var myButton: Button
    private lateinit var myText: EditText
    private lateinit var myTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        myButton = findViewById(R.id.button)
        myTextView = findViewById(R.id.textView)
        myText = findViewById(R.id.editTextText)


        myButton.setOnClickListener {
            val input = myText.text.toString()
            val intinput = input.toFloatOrNull()
            if (intinput != null) {
                val result = intinput * 2.54f  // multiply floats
                myTextView.text = input + " inches in cm is: " + result.toString()
            }
            else {
                myTextView.text = "Not a number..."
            }

        }
    }
}