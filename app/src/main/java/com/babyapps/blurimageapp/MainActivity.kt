package com.babyapps.blurimageapp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import com.babyapps.blurimageapp.databinding.ActivityMainBinding
import kotlin.math.max

class MainActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {

    private lateinit var binding: ActivityMainBinding

    var sourceBitmap: Bitmap? = null
    var destinationBitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sourceBitmap = BitmapFactory.decodeResource(this.resources, R.drawable.gingy)
        destinationBitmap = sourceBitmap!!.copy(sourceBitmap!!.config, true)

        binding.imageView.setImageBitmap(destinationBitmap)




        binding.seekBar.setOnSeekBarChangeListener(this)

        binding.btnFlip.setOnClickListener {
            flipImage(sourceBitmap!!, sourceBitmap!!)
            blurEffect()
        }

    }

    /**
     * A native method that is implemented by the 'blurimageapp' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    external fun flipImage(bitmapInput: Bitmap, bitmapOutput: Bitmap)
    external fun blurImage(bitmapInput: Bitmap, bitmapOutput: Bitmap, sigma: Float)


    companion object {
        // Used to load the 'blurimageapp' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }


    private fun blurEffect() {
        // Take value from Seek Bar(0-100) to 0.1-10
        val sigma = max(0.1F, binding.seekBar.progress / 10F)
        this.blurImage(sourceBitmap!!, destinationBitmap!!, sigma)
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        this.blurEffect()
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
    }
}