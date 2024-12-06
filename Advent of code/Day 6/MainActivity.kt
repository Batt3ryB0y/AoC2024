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
        val location = resources.openRawResource(R.raw.input_6)
        var amount = 0
        var steps = 0
        val data = location.bufferedReader().readLines()
        val arrayOfData = data.map { it.toCharArray() }.toMutableList()
        var x = -1
        var y = -1
        var orientation = ' '
        for (row in arrayOfData.indices)
        {
            for (col in arrayOfData[row].indices)
            {
                if (arrayOfData[row][col] == '<' || arrayOfData[row][col] == '>' || arrayOfData[row][col] == '^' || arrayOfData[row][col] == 'v')
                {
                    x = col
                    y = row
                    orientation = arrayOfData[row][col]
                    break
                }
            }
            if (x != -1) break

        }

        if (x != -1) {
            arrayOfData[y][x] = 'X'
        }


        while (true)
        {
            val movement = canMove(x, y, arrayOfData, orientation)
            if (movement == -1)
            {
                arrayOfData[y][x] = 'X'
                break
            }

            if (movement == 0)
            {
                orientation = when (orientation)
                {
                    '<' -> '^'
                    '>' -> 'v'
                    '^' -> '>'
                    'v' -> '<'
                    else -> orientation
                }
            }
            if (movement == 1)
            {
                arrayOfData[y][x] = 'X'
                when (orientation)
                {
                    '<' -> x--
                    '>' -> x++
                    '^' -> y--
                    'v' -> y++
                }
            }
            steps++

            if (steps > 150000 ) break
        }
        amount = arrayOfData.sumOf { row -> row.count() { it == 'X'} }
        val textOutput = findViewById<TextView>(R.id.textOutput)
        textOutput.text = "Amount: $amount, Steps: $steps"
    }

    private fun canMove(x: Int, y: Int, arrayOfData: MutableList<CharArray>, orientation: Char): Int
    {
        if (orientation == '<')
        {
            if (x - 1 < 0) return -1
            if (arrayOfData[y][x - 1] == '#') return 0
        }
        if (orientation == '>')
        {
            if (x + 1 >= arrayOfData[y].size) return -1
            if (arrayOfData[y][x + 1] == '#') return 0
        }
        if (orientation == '^')
        {
            if (y - 1 < 0) return -1
            if (arrayOfData[y - 1][x] == '#') return 0
        }
        if (orientation == 'v')
        {
            if (y + 1 >= arrayOfData.size) return -1
            if (arrayOfData[y + 1][x] == '#') return 0
        }
        return 1
    }

    private fun part2() {
        val location = resources.openRawResource(R.raw.input_6)
        var amount = 0
        val data = location.bufferedReader().readLines()
        val arrayOfData = data.map { it.toCharArray() }.toMutableList()
        var xO = -1
        var yO = -1
        var orientationO = ' '
        for (row in arrayOfData.indices)
        {
            for (col in arrayOfData[row].indices)
            {
                if (arrayOfData[row][col] == '<' || arrayOfData[row][col] == '>' || arrayOfData[row][col] == '^' || arrayOfData[row][col] == 'v')
                {
                    xO = col
                    yO = row
                    orientationO = arrayOfData[row][col]
                    break
                }
            }
        }


        for (row in arrayOfData.indices)
        {
            for (col in arrayOfData[row].indices)
            {
                var x = xO
                var y = yO
                val copy = arrayOfData.map { it.copyOf() }.toMutableList()
                var flag = false
                var orientation = orientationO
                if (copy[row][col] in listOf('<', '>', '^', 'v') || copy[row][col] == '#') continue
                else
                {
                    copy[row][col] = '#'

                    var steps = 0
                    val visited = mutableSetOf<Triple<Int, Int, Char>>()
                    while (true)
                    {
                        val state = Triple(x, y, orientation)

                        if (visited.contains(state)) {
                            flag = true
                            break
                        }
                        visited.add(state)


                        val movement = canMove(x, y, copy, orientation)
                        if (movement == -1) break

                        if (movement == 0)
                        {
                            orientation = when (orientation)
                            {
                                '<' -> '^'
                                '>' -> 'v'
                                '^' -> '>'
                                'v' -> '<'
                                else -> orientation
                            }
                        }
                        if (movement == 1)
                        {
                            copy[y][x] = orientation
                            when (orientation) {
                                '<' -> x--
                                '>' -> x++
                                '^' -> y--
                                'v' -> y++
                            }
                        }
                        steps++
                        if(steps > 10000) break
                    }
                    if (flag) amount++

                }
            }
        }

        val textOutput = findViewById<TextView>(R.id.textOutput)
        textOutput.text = "Amount: $amount"
    }


}