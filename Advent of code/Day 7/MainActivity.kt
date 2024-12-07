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

        part1()
//        part2()
    }

    private fun part1() {
        val location = resources.openRawResource(R.raw.input_7)
        val data = location.bufferedReader().readLines()
        val lines = data.toMutableList()
        var out = 0L
        for (line in lines)
        {
            if (line.isEmpty()) continue
            val goal = line.split(':')[0].toLong()
            val parts = line.split(':')[1].split(' ').filter { it.isNotEmpty() }.map { it.toLong() }
            if (testOperators(parts, goal)) out += goal

        }
        val textOutput = findViewById<TextView>(R.id.textOutput)
        textOutput.text = out.toString()
    }

    private fun testOperators(items : List<Long>, goal : Long) : Boolean
    {
        val operators = listOf('+', '*')            // FOR PART 1
//        val operators = listOf('+', '*', '|')     // FOR PART 2
        val combinations = recGenerateCombinations(operators, items.size - 1)
        for (combination in combinations)
        {
            if (evaluate(items, combination) == goal) return true
        }
        return false
    }

    private fun evaluate(items : List<Long>, operators : List<Char>) : Long
    {
        var result = items[0]
        for (i in 1 until items.size) {
            if (operators[i - 1] == '+') result += items[i]
            else if (operators[i - 1] == '*') result *= items[i]
            else if (operators[i - 1] == '|') result = (result.toString() + items[i].toString()).toLong()
            else throw Exception("Unknown operator")
        }
        return result
    }

    private fun recGenerateCombinations(operators : List<Char>, length : Int) : List<List<Char>>
    {
        if (length == 0) return listOf(emptyList())
        val step = recGenerateCombinations(operators, length - 1)
        val result = mutableListOf<List<Char>>()
        for (item in step)
        {
            for (operator in operators)
            {
                result.add(item + operator)
            }
        }
        return result
    }

    private fun part2() {

    }


}