package com.example.wallpaper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class Myadapter(var item: Array<String>, var mainActivity: MainActivity, var wpaper: Array<Int>): BaseAdapter() {
    override fun getCount(): Int {

        return item.size
    }
    override fun getItem(p0: Int): Any {
        return p0
    }
    override fun getItemId(p0: Int): Long {
    return 0
    }
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        var leyoutInflater:LayoutInflater=LayoutInflater.from(mainActivity)

        var view:View=leyoutInflater.inflate(R.layout.myitem,null)

        var textView=view.findViewById<TextView>(R.id.mytext)
        textView.text=item[p0]

        var imagev=view.findViewById<ImageView>(R.id.myimage)
        imagev.setImageResource(wpaper[p0])

            return view

    }
}