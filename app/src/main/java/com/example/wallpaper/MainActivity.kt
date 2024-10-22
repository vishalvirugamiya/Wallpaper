package com.example.wallpaper

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.GridView
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

open class MainActivity : AppCompatActivity() {

    lateinit var listView: GridView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        listView=findViewById(R.id.mylist)

        var item=arrayOf("Abstract","Animal","Artistic","Astronomy","City & Buildings",)
        var wpaper= arrayOf(R.drawable.abstract6,R.drawable.animal6,R.drawable.artistic7,
                            R.drawable.astro1,R.drawable.bg7,)

        var myarray=Myadapter(item,this,wpaper)
        listView.adapter=myarray

        listView.setOnItemClickListener { parent, view, position, id ->

            //Toast.makeText(this@MainActivity,"$position",Toast.LENGTH_SHORT).show()

            var intent=Intent(this@MainActivity,MainActivity2::class.java)
            intent.putExtra("position",position)
            startActivity(intent)
        }


    }


}