package com.hoony.z_giyo.viewer

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.hoony.z_giyo.R
import kotlinx.android.synthetic.main.activity_viewer.*

class ViewerActivity : AppCompatActivity(), View.OnClickListener {

    private var toggle: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewer)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE or
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

        tvText.text = intent.getStringExtra("title")
        tvText.startScroll()

        clContainer.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.clContainer -> {
                toggleColor()
                playSound()
            }
        }
    }

    private fun toggleColor() {
        if (toggle) {
            clContainer.setBackgroundColor(ContextCompat.getColor(this, R.color.black))
            tvText.setTextColor(ContextCompat.getColor(this, R.color.white))
        } else {
            clContainer.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
            tvText.setTextColor(ContextCompat.getColor(this, R.color.black))
        }
        toggle = !toggle
    }

    private fun playSound() {
        val mediaPlayer = MediaPlayer.create(this, R.raw.bell)
        mediaPlayer.setOnCompletionListener {
            it.release()
        }
        mediaPlayer.start()
    }
}