package edu.ydjs.mathpro6

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.*

/* -----------------------------------------------
   -- MODEL
   ------------------------------------------------ */
data class MathQuestion(
    val question: String,
    val answer: Int,
    val points: Int
)

/* -----------------------------------------------
   -- VIEWMODEL
   ------------------------------------------------ */
class MathViewModel : ViewModel() {
    private val operand = arrayOf(1,2,3,4,5,6,7,8,9,0)
    private val operator = arrayOf("+", "-")
    private val hardOperator = arrayOf("*", "/")

    private val _question = MutableLiveData<MathQuestion>()
    val question: LiveData<MathQuestion> get() = _question

    private val _score = MutableLiveData(0)
    val score: LiveData<Int> get() = _score

    var currentLevel = 1

    fun generateQuestion(level: Int) {
        val rand = { operand.random() }
        val small = { rand() }
        val mid = { rand() + rand() * 10 }
        val big = { rand() + rand() * 10 + rand() * 100 }

        val left: Int
        val right: Int
        val op: String
        val q: String
        val a: Int
        val p: Int

        when (level) {
            1 -> {
                left = small()
                right = small()
                op = operator.random()
                q = "$left$op$right"
                a = solve(q)
                p = 1
            }
            2 -> {
                left = mid()
                right = mid()
                op = operator.random()
                q = "$left$op$right"
                a = solve(q)
                p = 2
            }
            3 -> {
                left = big()
                right = big()
                op = operator.random()
                q = "$left$op$right"
                a = solve(q)
                p = 5
            }
            4, 5, 6 -> {
                val size = when (level) {
                    4 -> small()
                    5 -> mid()
                    else -> big()
                }

                op = hardOperator.random()
                if (op == "/") {
                    right = size.coerceAtLeast(1)
                    a = size
                    left = right * a
                } else {
                    left = size
                    right = size
                    a = solve("$left$op$right")
                }
                q = "$left$op$right"
                p = when (level) {
                    4 -> 10
                    5 -> 15
                    else -> 30
                }
            }
            else -> {
                q = "0+0"
                a = 0
                p = 0
            }
        }

        _question.value = MathQuestion(q, a, p)
    }

    fun checkAnswer(input: String) {
        val correct = question.value?.answer?.toString() ?: return
        if (input == correct) {
            _score.value = _score.value?.plus(question.value?.points ?: 0)
            generateQuestion(currentLevel)
        }
    }

    private fun solve(expr: String): Int {
        val i = expr.indexOfFirst { it in "+-*/" }
        val left = expr.substring(0, i).toInt()
        val op = expr[i]
        val right = expr.substring(i + 1).toInt()
        return when (op) {
            '+' -> left + right
            '-' -> left - right
            '*' -> left * right
            '/' -> if (right != 0) left / right else 0
            else -> 0
        }
    }
}

/* -----------------------------------------------
   -- VIEW (MainActivity)
   ------------------------------------------------ */
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MathViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val seekBar = findViewById<SeekBar>(R.id.seekBar)
        val textViewLevel = findViewById<TextView>(R.id.textView_level)
        val textViewQ = findViewById<TextView>(R.id.textView)
        val textViewPoint = findViewById<TextView>(R.id.textView_point)
        val editText = findViewById<EditText>(R.id.editText)
        val button = findViewById<Button>(R.id.button)
        val buttonGo = findViewById<Button>(R.id.buttonGo)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel = ViewModelProvider(this)[MathViewModel::class.java]

        // Observe question
        viewModel.question.observe(this) {
            textViewQ.text = "${it.question} = ? (+${it.points})"
        }

        // Observe score
        viewModel.score.observe(this) {
            textViewPoint.text = "Points: $it"
            if (it >= 100) {
                Toast.makeText(this, getString(R.string.nice_message), Toast.LENGTH_SHORT).show()
            }
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {
                val level = progress + 1
                viewModel.currentLevel = level
                textViewLevel.text = getString(R.string.level_with_number, level)
            }

            override fun onStartTrackingTouch(sb: SeekBar?) {}
            override fun onStopTrackingTouch(sb: SeekBar?) {}
        })

        buttonGo.setOnClickListener {
            viewModel.generateQuestion(viewModel.currentLevel)
        }

        button.setOnClickListener {
            val input = editText.text.toString()
            viewModel.checkAnswer(input)
            editText.setText("")
        }
    }
}
