package com.example.simplelist

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputNumber = findViewById<EditText>(R.id.input_number)
        val radioGroup = findViewById<RadioGroup>(R.id.radio_group)
        val buttonShow = findViewById<Button>(R.id.button_show)
        val listView = findViewById<ListView>(R.id.number_list_view)
        val errorMessage = findViewById<TextView>(R.id.error_message)

        buttonShow.setOnClickListener {
            val input = inputNumber.text.toString()
            if (input.isEmpty()) {
                errorMessage.text = "Please enter a number"
                return@setOnClickListener
            }

            val n = input.toIntOrNull()
            if (n == null || n <= 0) {
                errorMessage.text = "Please enter a valid positive integer"
                return@setOnClickListener
            }

            errorMessage.text = ""
            val selectedRadioButtonId = radioGroup.checkedRadioButtonId
            val numbers = when (selectedRadioButtonId) {
                R.id.radio_even -> generateEvenNumbers(n)
                R.id.radio_odd -> generateOddNumbers(n)
                R.id.radio_square -> generatePerfectSquares(n)
                else -> {
                    errorMessage.text = "Please select a number type"
                    return@setOnClickListener
                }
            }

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbers)
            listView.adapter = adapter
        }
    }

    private fun generateEvenNumbers(n: Int): List<Int> {
        return (0..n).filter { it % 2 == 0 }
    }

    private fun generateOddNumbers(n: Int): List<Int> {
        return (1..n).filter { it % 2 != 0 }
    }

    private fun generatePerfectSquares(n: Int): List<Int> {
        val squares = mutableListOf<Int>()
        var i = 0
        while (i * i <= n) {
            squares.add(i * i)
            i++
        }
        return squares
    }
}