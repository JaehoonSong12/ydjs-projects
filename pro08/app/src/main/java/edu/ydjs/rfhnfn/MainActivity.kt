package edu.ydjs.rfhnfn

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import edu.ydjs.rfhnfn.ui.theme.RfhnfnTheme
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat

class MainActivity : ComponentActivity() {
    private lateinit var myButton: Button
    private lateinit var myTextView : TextView
    private lateinit var myEditText : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myButton = findViewById(R.id.myButton)
        myTextView = findViewById(R.id.myTextView)
        myEditText = findViewById(R.id.myEditText)

        myButton.text = getString(R.string.btn_name)
        myButton.setOnClickListener {
            if (myEditText.text.toString().isNotEmpty()) {
                val input = myEditText.text.toString()

                myTextView.visibility = View.VISIBLE
                myTextView.text = input
                myTextView.setTextColor(ContextCompat.getColor(this, R.color.black))
            } else {
                myTextView.visibility = View.VISIBLE
                myTextView.text = getString(R.string.text_view_empty)
                myTextView.setTextColor(ContextCompat.getColor(this, R.color.red))
            }

        }

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
    RfhnfnTheme {
        Greeting("Android")
    }
}