package com.example.advent_of_code

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val location = resources.openRawResource(R.raw.input_2)
        val data = location.bufferedReader().readText()
        var sum = 0
        var list = mutableListOf<Int>()

        val lines = data.split('\n')
        for (line in lines)
        {
            if (line.isEmpty()) continue
            val parts = line.split(' ')
            list = parts.map { it.toInt() }.toMutableList()


            var flag = true

            for (j in 0 until list.size) {          // this is for indexing the removal of an element
                val list_copy = list.toMutableList()
                list_copy.removeAt(j)
                var ascending = true
                if (list_copy[0] > list_copy[1]) ascending = false
                flag = true
                for (i in 1 until list_copy.size)
                {


                    val diff = abs(list_copy[i] - list_copy[i - 1])
                    if (diff > 3 || diff < 1) {
                        flag = false
                        break
                    }
                    if (ascending && list_copy[i] < list_copy[i - 1]) {
                        flag = false
                        break
                    }
                    if (!ascending && list_copy[i] > list_copy[i - 1]) {
                        flag = false
                        break
                    }
                }
                if (flag) {
                    break
                }
            }
            if (flag) sum++
        }

        findViewById<TextView>(R.id.textOutput).text = sum.toString()



    }
}