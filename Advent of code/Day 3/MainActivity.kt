package com.example.advent_of_code

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

//        part1()
//        part2()
    }

    private fun part1() {
        val location = resources.openRawResource(R.raw.input_3)
        val data = location.bufferedReader().readText()
        val regex = Regex("mul\\([0-9]+,[0-9]+\\)")
        var amount = 0
        val matches = regex.findAll(data)
        for (match in matches)
        {
            val matchString = match.value.substring(4, match.value.length - 1)
            val numbers = matchString.split(",")
            val firstNumber = numbers[0].toInt()
            val secondNumber = numbers[1].toInt()
            amount += firstNumber * secondNumber
        }
        val textOutput = findViewById<TextView>(R.id.textOutput)
        textOutput.text = amount.toString()
    }

    private fun part2() {
        val location = resources.openRawResource(R.raw.input_3)
        val data = location.bufferedReader().readText()
        val regex = Regex("mul\\([0-9]+,[0-9]+\\)|do\\(\\)|don't\\(\\)")
        var amount = 0
        val matches = regex.findAll(data)
        var doFlag = true
        for (match in matches)
        {
            if (match.value == "do()")
            {
                doFlag = true
                continue
            }
            if (match.value == "don't()")
            {
                doFlag = false
                continue
            }
            if (!doFlag) continue
            val matchString = match.value.substring(4, match.value.length - 1)
            val numbers = matchString.split(",")
            val firstNumber = numbers[0].toInt()
            val secondNumber = numbers[1].toInt()
            amount += firstNumber * secondNumber
        }
        val textOutput = findViewById<TextView>(R.id.textOutput)
        textOutput.text = amount.toString()
    }
}