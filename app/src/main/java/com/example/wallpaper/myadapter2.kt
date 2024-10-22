package com.example.wallpaper
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView

class myadapter2( var mainActivity2: MainActivity2, var  array1: Array<Int>) : BaseAdapter() {
    override fun getCount(): Int {
       return array1.size
    }

    override fun getItem(p0: Int): Any {

        return p0
    }

    override fun getItemId(p0: Int): Long {

        return 0
    }

    @SuppressLint("MissingInflatedId")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var leyoutInflater: LayoutInflater = LayoutInflater.from(mainActivity2)
        var view:View=leyoutInflater.inflate(R.layout.myitem2,null)


        var imagev=view.findViewById<ImageView>(R.id.image_page2)
        imagev.setImageResource(array1[p0])


        return view

    }

    }
