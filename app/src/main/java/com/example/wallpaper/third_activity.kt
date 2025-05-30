package com.example.wallpaper

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.app.WallpaperManager
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Binder
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.wallpaper.databinding.ActivityThirdBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.concurrent.Executors
import kotlin.random.Random


class third_activity : AppCompatActivity() {

    lateinit var binding: ActivityThirdBinding

//    lateinit var previos: ImageView
//    lateinit var nextB: ImageView
//    lateinit var bottst: ImageView

    lateinit var viewPager: ViewPager

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
         enableEdgeToEdge()
        setContentView(binding.root)

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        var position = intent.getIntExtra("position", 0)
        var currImage = intent.getIntArrayExtra("list")!!


        viewPager = findViewById(R.id.viewPager)

        var adapter3 = Adapter3(this, currImage)
        viewPager.adapter = adapter3
        viewPager.setCurrentItem(position)


       // viewPager.currentItem

      //  previos = findViewById(R.id.previos)
        binding.previos.setOnClickListener {
                viewPager.setCurrentItem(viewPager.currentItem-1)
        }

      //  nextB = findViewById(R.id.nextbatoon)
        binding.nextbatoon.setOnClickListener {

            viewPager.setCurrentItem(viewPager.currentItem+1)

        }

       // bottst = findViewById(R.id.bottonset_)
        binding.bottonset.setOnClickListener {

            shwBottomdialog(currImage[viewPager.currentItem])
            
        }
    }

    private fun shwBottomdialog(imgResouceId: Int) {

        var bottonSTdialog = BottomSheetDialog(this@third_activity)

        bottonSTdialog.setContentView(R.layout.bottomsheet)

        var sherbtton = bottonSTdialog.findViewById<ImageView>(R.id.shereBT)
        sherbtton?.setOnClickListener {

            shareWallpaper(imgResouceId)
        }


        var dwBtton = bottonSTdialog.findViewById<ImageView>(R.id.downlodBT)            //downlod batton
        dwBtton?.setOnClickListener {

            downlodWallpaper(imgResouceId)
        }


        var setWallp = bottonSTdialog.findViewById<ImageView>(R.id.setwallpBT)          //set wallpaper batton
        setWallp?.setOnClickListener {

            setwallpalper(imgResouceId)

        }
        bottonSTdialog.show()
    }

    private fun setwallpalper(imgResouceId: Int) {
        val progress = ProgressDialog(this)
        progress.setTitle("Loading")
        progress.setMessage("Wait while loading...")
        progress.setCancelable(false) // disable dismiss by tapping outside of the dialog
        progress.show()

        Executors.newSingleThreadExecutor().execute {

            val myWallpaperManager = WallpaperManager.getInstance(this@third_activity)
            try {
                myWallpaperManager.setResource(imgResouceId)

                runOnUiThread {
                    progress.dismiss()
                    Toast.makeText(this@third_activity, "Set Wallpaper ", Toast.LENGTH_SHORT)
                        .show()
                    progress.dismiss()
                }

            } catch (e: IOException) {

                Toast.makeText(this@third_activity, "Error", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }
    }


    private fun downlodWallpaper(imgResouceId: Int) {

        val progress = ProgressDialog(this)
        progress.setTitle("Loading")
        progress.setMessage("Wait while loading...")
        progress.setCancelable(false) // disable dismiss by tapping outside of the dialog
        progress.show()

        //Background Thread ->
        //Ui thread ->

        Executors.newSingleThreadExecutor().execute {
            var mypath = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                "${Random.nextInt()}.jpg"
            )
            var fos: FileOutputStream? = null
            try {
                fos = FileOutputStream(mypath)
                var bitmapImage = BitmapFactory.decodeResource(resources, imgResouceId)
                // Use the compress method on the BitMap object to write image to the OutputStream
                bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, fos)

                runOnUiThread {
                    Toast.makeText(this@third_activity, "Download successful", Toast.LENGTH_SHORT)
                        .show()
                    progress.dismiss()
                }

            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                try {
                    fos!!.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }

    }
    private fun shareWallpaper(imgResouceId: Int) {

        val b = BitmapFactory.decodeResource(resources, imgResouceId)
        getimageTosher(b)

    }
    private fun getimageTosher(b: Bitmap) {

        val progress = ProgressDialog(this)
        progress.setTitle("Loading")
        progress.setMessage("Wait while loading...")
        progress.setCancelable(false) // disable dismiss by tapping outside of the dialog
        progress.show()

        Executors.newSingleThreadExecutor().execute {
            val share = Intent(Intent.ACTION_SEND)
            share.setType("image/jpeg")
            val bytes = ByteArrayOutputStream()
            b.compress(Bitmap.CompressFormat.JPEG, 100, bytes)

            runOnUiThread {
                progress.dismiss()
            }

            val path = MediaStore.Images.Media.insertImage(contentResolver, b, "Title", null)
            val imageUri = Uri.parse(path)
            share.putExtra(Intent.EXTRA_STREAM, imageUri)
            startActivity(share)
        }
    }

}

