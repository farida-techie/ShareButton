package com.example.sharebutton

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





    }
    fun clickOnSharetxtbtn(view: View){
        val s = sharingText.text.toString()

        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT,s)
        shareIntent.putExtra(Intent.EXTRA_SUBJECT,"subject here")
        startActivity(Intent.createChooser(shareIntent,"shre text via"))

    }
    fun  clickOnShareImgbtn(view: View){

        val myDrawable = sharingImage.drawable

        val bitmap = (myDrawable as BitmapDrawable).bitmap

        val file = File(externalCacheDir,"my Image.png")
        val fOut = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.PNG,90,fOut)
        fOut.flush()
        fOut.close()
        file.setReadable(true,false)
        val intent = Intent(Intent.ACTION_SEND)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK;
        intent.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(file))
        intent.type = "Image/png"
        intent.putExtra(Intent.EXTRA_SUBJECT,"subject here")
        startActivity(Intent.createChooser(intent,"Share Image Via"))


    }
}
