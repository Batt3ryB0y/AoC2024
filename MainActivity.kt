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
        val location = resources.openRawResource(R.raw.input_1)
        val data = location.bufferedReader().readText()
        var sum = 0
        val list1 = mutableListOf<Int>()
        val list2 = mutableListOf<Int>()

        val lines = data.split('\n')
        for (line in lines)
        {
            if (line.isEmpty()) continue
            val parts = line.split(' ')
            list1.add(parts[0].toInt())
            list2.add(parts[parts.size-1].toInt())
        }
//        list1.sort()
//        list2.sort()
//        while (list1.size>0 && list2.size>0)
//        {
//            val diff = abs(list1[0]-list2[0])
//            list1.removeAt(0)
//            list2.removeAt(0)
//            sum+=diff
//        }

        for (i in 0 until list1.size)
        {
            sum += (list2.count { list1[i] == it} * list1[i])
        }
        findViewById<TextView>(R.id.textOutput).text = sum.toString()



    }
}