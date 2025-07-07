package edu.ydjs.wsgahahaha

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
    private lateinit var myTextView: TextView
    private lateinit var myEditText: EditText
    private var repeatCount = 1
    private var previousInput: String = ""
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
        myEditText = findViewById(R.id.editText)

        myTextView.text = getString(R.string.text_cv)


        myButton.setOnClickListener {
            val input = myEditText.text.toString()
            if (input != previousInput) {
                repeatCount = 1  // reset if input changed
            }

            if (input.isNotEmpty()) {
                previousInput = input
                myTextView.text = input.repeat(repeatCount)
                repeatCount++
            }

        }

    }
}