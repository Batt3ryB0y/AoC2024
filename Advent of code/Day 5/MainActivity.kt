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
        part2()
    }

    private fun part1() {
        val location = resources.openRawResource(R.raw.input_5)
        var amount = 0
        val data = location.bufferedReader().readLines()
        var dict = mutableMapOf<Int, MutableList<Int>>()
        var switch = true
        for (line in data)
        {
            if (line.isEmpty() && switch) switch = false
            if (switch)
            {
                val values = line.split("|")
                if (!dict.containsKey(values[0].toInt()))
                {
                    dict[values[0].toInt()] = mutableListOf()
                }
                dict[values[0].toInt()]?.add(values[1].toInt())
            }
            else
            {
                if (line.isEmpty()) continue
                val items = line.split(",").map { it.toInt() }
                var valid = true
                for (item in items.indices)
                {
                    for (item2 in item + 1 until items.size)
                    {
                        if (dict[items[item2]]?.contains(items[item]) == true) {
                            valid = false
                            break
                        }
                    }
                    if (!valid) break
                }
                if (valid)
                    amount += items[(items.size / 2)]
            }
        }
        val textOutput = findViewById<TextView>(R.id.textOutput)
        textOutput.text = amount.toString()
    }

    private fun part2() {
        val location = resources.openRawResource(R.raw.input_5)
        var amount = 0
        val data = location.bufferedReader().readLines()
        var dict = mutableMapOf<Int, MutableList<Int>>()
        var switch = true
        for (line in data)
        {
            if (line.isEmpty() && switch) switch = false
            if (switch)
            {
                var values = line.split("|")
                if (!dict.containsKey(values[0].toInt()))
                {
                    dict[values[0].toInt()] = mutableListOf()
                }
                dict[values[0].toInt()]?.add(values[1].toInt())
            }
            else
            {
                if (line.isEmpty()) continue
                val items = line.split(",").map { it.toInt() }
                var valid = true
                for (item in items.indices)
                {
                    for (item2 in item + 1 until items.size)
                    {
                        if (dict[items[item2]]?.contains(items[item]) == true) {
                            valid = false
                            amount += fixSequence(items, dict, item, item2)
                            break
                        }
                    }
                    if (!valid) break
                }

            }
        }
        val textOutput = findViewById<TextView>(R.id.textOutput)
        textOutput.text = amount.toString()
    }

    private fun fixSequence(vals : List<Int>, dict : MutableMap<Int, MutableList<Int>>, first : Int, second : Int) :Int
    {
        val items = vals.toMutableList()
        while (true)
        {
            var flag = false
            for (item in items.indices)
            {
                for (item2 in item + 1 until items.size)
                {
                    if (dict[items[item2]]?.contains(items[item]) == true)
                    {
                        val temp = items[item]
                        items[item] = items[item2]
                        items[item2] = temp
                        flag = true
                        break
                    }
                }
                if (flag) break
            }
            if (flag) continue
            break
        }
        return items[items.size / 2]
    }

    private fun checkSequence(items : List<Int>, dict : MutableMap<Int, MutableList<Int>>) :Boolean
    {
        var valid = true
        for (item in items.indices)
        {
            for (item2 in item + 1 until items.size)
            {
                if (dict[items[item2]]?.contains(items[item]) == true) {
                    valid = false
                    break
                }
            }
            if (!valid) return false
        }
        return true
    }

}