//package com.example.wallpaper
//
//import android.annotation.SuppressLint
//import android.app.WallpaperManager
//import android.content.Intent
//import android.graphics.Bitmap
//import android.graphics.BitmapFactory
//import android.net.Uri
//import android.os.Bundle
//import android.os.Environment
//import android.widget.ImageView
//import android.widget.Toast
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.content.FileProvider
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import com.google.android.material.bottomsheet.BottomSheetDialog
//import java.io.File
//import java.io.FileOutputStream
//import java.io.IOException
//import kotlin.random.Random
//
//
//class third_activity_temp : AppCompatActivity() {
//
//    lateinit var imageV: ImageView
//    lateinit var previos: ImageView
//    lateinit var nextB: ImageView
//    lateinit var bottst: ImageView
//
//    @SuppressLint("MissingInflatedId")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_third)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//
//        var position = intent.getIntExtra("position", 0)
//        var currImage = intent.getIntArrayExtra("list")
//
//        imageV = findViewById(R.id.image_page3)
//        imageV.setImageResource(currImage!![position])
//
//        previos = findViewById(R.id.previos)
//        previos.setOnClickListener {
//            position--
//            imageV.setImageResource(currImage!![position])
//
//        }
//        nextB = findViewById(R.id.nextbatoon)
//        nextB.setOnClickListener {
//            position++
//            imageV.setImageResource(currImage!![position])
//
//        }
//
//        bottst = findViewById(R.id.bottonset_)
//        bottst.setOnClickListener {
//
//            //   Toast.makeText(this@third_activity,"postion",Toast.LENGTH_SHORT).show()
//            shwBottomdialog(currImage!![position])
//        }
//    }
//
//    private fun shwBottomdialog(imgResouceId: Int) {
//
//        var bottonSTdialog = BottomSheetDialog(this@third_activity_temp)
//        bottonSTdialog.setContentView(R.layout.bottomsheet)
//
//        var sherbtton = bottonSTdialog.findViewById<ImageView>(R.id.shereBT)
//        sherbtton?.setOnClickListener {
//shareWallpaper(imgResouceId)
//        }
//
//        var dwBtton =
//            bottonSTdialog.findViewById<ImageView>(R.id.downlodBT)            //downlod batton
//        dwBtton?.setOnClickListener {
//            downloadImage(imgResouceId)
//        }
//
//        var setWallp =
//            bottonSTdialog.findViewById<ImageView>(R.id.setwallpBT)                  //set wallpaper batton
//        setWallp?.setOnClickListener {
//            val myWallpaperManager = WallpaperManager.getInstance(this@third_activity_temp)
//            try {
//                myWallpaperManager.setResource(imgResouceId)
//                bottonSTdialog.dismiss()
//                Toast.makeText(this@third_activity_temp, "Set Wallpaper ", Toast.LENGTH_SHORT).show()
//            } catch (e: IOException) {
//
//                Toast.makeText(this@third_activity_temp, "Error", Toast.LENGTH_SHORT).show()
//                e.printStackTrace()
//            }
//        }
//
//        bottonSTdialog.show()
//
//    }
//
//    private fun shareWallpaper(imgResouceId: Int) {
//        var bitmapImage = BitmapFactory.decodeResource(resources, imgResouceId)
//        val uri: Uri = getmageToShare(bitmapImage)!!
//        val intent = Intent(Intent.ACTION_SEND)
//        // putting uri of image to be shared
//        intent.putExtra(Intent.EXTRA_STREAM, uri)
//        // adding text to share
//        intent.putExtra(Intent.EXTRA_TEXT, "This is From My Wallpaper App")
//        // setting type to image
//        intent.setType("image/*")
//
//
//        // calling startactivity() to share
//        startActivity(Intent.createChooser(intent, "Share Via"))
//    }
//
//    private fun getmageToShare(bitmap: Bitmap): Uri? {
//        val imagefolder = File(cacheDir, "images")
//        var uri: Uri? = null
//        try {
//            imagefolder.mkdirs() // for create images folder in cache Directory
//            val file = File(imagefolder, "img.png")
//            val outputStream = FileOutputStream(file)
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
//            outputStream.flush()
//            outputStream.close()
//            uri = FileProvider.getUriForFile(this, packageName, file)
//        } catch (e: java.lang.Exception) {
//            Toast.makeText(this, "" + e.message, Toast.LENGTH_LONG).show()
//        }
//        return uri
//    }
//
//
//    private fun downloadImage(imgResouceId: Int) {
//
//        var mypath = File(
//            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
//            "${Random.nextInt()}.jpg"
//        )
//
//        var fos: FileOutputStream? = null
//        try {
//            fos = FileOutputStream(mypath)
//
//            // Drawable image to Bitmap
//            var bitmapImage = BitmapFactory.decodeResource(resources, imgResouceId)
//
//            // Use the compress method on the BitMap object to write image to the OutputStream
//            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, fos)
//
//            Toast.makeText(this@third_activity_temp,"Download Successful",Toast.LENGTH_SHORT).show()
//
//        } catch (e: Exception) {
//            e.printStackTrace()
//        } finally {
//            try {
//                fos!!.close()
//            } catch (e: IOException) {
//                e.printStackTrace()
//            }
//        }
//
//
//
//    }
//
//}
//
