package com.example.wallpaper

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.GridView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {

    lateinit var listV: GridView

    var wallp1 = arrayOf(
        R.drawable.abstract1, R.drawable.abstract2, R.drawable.abstract3,
        R.drawable.abstract4, R.drawable.abstract5, R.drawable.abstract6,
        R.drawable.abstract7, R.drawable.abstract8, R.drawable.abstract9,
        R.drawable.abstract10, R.drawable.abstract11, R.drawable.abstract12)

    var wallp2 = arrayOf(
        R.drawable.animal1, R.drawable.animal2, R.drawable.animal3, R.drawable.animal4,
        R.drawable.animal5, R.drawable.animal6, R.drawable.anima7, R.drawable.animal8,
        R.drawable.animal9, R.drawable.animal10, R.drawable.animal11, R.drawable.animal12)

     var wallp3 = arrayOf(
         R.drawable.artistic1,R.drawable.artistic2,R.drawable.artistic3, R.drawable.artistic4,
         R.drawable.artistic5,R.drawable.artistic6,R.drawable.artistic7,R.drawable.artistic8,
         R.drawable.artistic9,R.drawable.artistic10,R.drawable.artistic11,R.drawable.artistic12)

    var wallp4 = arrayOf(
        R.drawable.astro1,R.drawable.astro2,R.drawable.astro3,R.drawable.astro4,
        R.drawable.astro5,R.drawable.astro6,R.drawable.astro7,R.drawable.astro8,
        R.drawable.astro9,R.drawable.astro10,R.drawable.astro11,R.drawable.astro12)

    var wallp5= arrayOf(
        R.drawable.bg1,R.drawable.bg2,R.drawable.bg3,R.drawable.bg4,
        R.drawable.bg5,R.drawable.bg6,R.drawable.bg7,R.drawable.bg8,
        R.drawable.bg9,R.drawable.bg10,R.drawable.bg11,R.drawable.bg12,)





    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var position = intent.getIntExtra("position", 0)

        //Toast.makeText(this@MainActivity2, "$position", Toast.LENGTH_LONG).show()

        listV = findViewById(R.id.secList)

        var array1 = arrayOf<Int>()

        if (position == 0) {
            array1 = wallp1
        } else if (position == 1)
        {
            array1 = wallp2
        }else if(position==2)
        {
            array1=wallp3
        }else if (position==3)
        {
            array1=wallp4
        }else if (position==4)
        {
            array1=wallp5
        }


        var myadapter = myadapter2(this@MainActivity2, array1)
        listV.adapter = myadapter

        listV.setOnItemClickListener { parent, view, position, id ->

           // Toast.makeText(this@MainActivity2, "$position", Toast.LENGTH_SHORT).show()

            var intent = Intent(this@MainActivity2, third_activity::class.java)
            intent.putExtra("position", position)
            intent.putExtra("list", array1.toIntArray())
//            intent.putExtra("list",IntArray(10))
            startActivity(intent)

        }

    }


}