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
    }

    private fun part1() {
        val location = resources.openRawResource(R.raw.input_4)
        var data = location.bufferedReader().readText().lines().map { it.toCharArray() }.toTypedArray()
        var amount = 0
        val directions = listOf(
            Pair(0, 1),
            Pair(1, 0),
            Pair(0, -1),
            Pair(-1, 0),
            Pair(1, 1),
            Pair(1, -1),
            Pair(-1, 1),
            Pair(-1, -1)
        )
        for (i in data.indices)
        {
            for (j in data[i].indices)
            {
                for (direction in directions)
                {
//                    amount += regexFind(direction, i, j, data)
                }
                amount += regexFind2(i, j, data)
            }
        }
        val textOutput = findViewById<TextView>(R.id.textOutput)
        textOutput.text = amount.toString()
    }

    private fun regexFind(direction: Pair<Int, Int>, i: Int, j: Int, data: Array<CharArray>): Int
    {
        val regex = "XMAS"
        try {
            val str = data[i][j].toString() + data[i - direction.first][j - direction.second] + data[i - 2 * direction.first][j - 2 * direction.second] + data[i - 3 * direction.first][j - 3 * direction.second]
            if (regex == str)
            {
                return 1
            }
        }
        catch (e: Exception)
        {
            return 0
        }
        return 0
    }
    private fun regexFind2(i: Int, j: Int, data: Array<CharArray>): Int
    {
        val regex = "MAS"
        val regex2 = "SAM"

        // I was checking the out of bounds by simply asking if i - 1 < 0 and so on, but the app kept crashing for some reason
        try {
            val str1 = "${data[i - 1][j - 1]}" + "${data[i][j]}" + "${data[i + 1][j + 1]}"
            val str2 = "${data[i - 1][j + 1]}" + "${data[i][j]}" + "${data[i + 1][j - 1]}"
            if ((str1 == regex || str1 == regex2) && (str2 == regex || str2 == regex2)) {
                return 1
            }
        }
        catch (e: Exception)
        {
            return 0
        }


        return 0
    }


}