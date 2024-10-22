package com.example.wallpaper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter

class Adapter3(var thirdActivity: third_activity, var currImage: IntArray) : PagerAdapter() {
    override fun getCount(): Int {

        return currImage.size

    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {

        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        var inflater = LayoutInflater.from(thirdActivity)
        var view = inflater.inflate(R.layout.item_3, null)

        var imageView = view.findViewById<ImageView>(R.id.imageView)
        imageView.setImageResource(currImage[position])

        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

        container.removeView(`object` as View)
    }
}