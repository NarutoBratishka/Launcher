package com.mentalscapes

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout

class LoadingScreenActivity : AppCompatActivity() {
    lateinit var progress: ImageView
    lateinit var layoutParams: ConstraintLayout.LayoutParams
    var maxProgressWidth: Float = 447F
    var progressWidth: Float = 1F



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading_screen)
        /*Toast.makeText(this,
            maxProgressWidth.toString(),
            Toast.LENGTH_LONG)
            .show()*/
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        supportActionBar?.hide()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE or
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

        maxProgressWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 447F, resources.displayMetrics)
        progress = findViewById(R.id.progress_center)

        Thread(Runnable { loading() }
        ).start()
    }


    fun loading() {
        var startTime = System.currentTimeMillis()
        var currentWidth = progressWidth
        var rand = 0
        val stillNotFull = maxProgressWidth - 50

        while (currentWidth < stillNotFull) {
            rand = (Math.random() * (Math.random() * (Math.random() * 2))).toInt()
            progressWidth = currentWidth + rand

            layoutParams = ConstraintLayout.LayoutParams(
                progressWidth.toInt(), ConstraintLayout.LayoutParams.WRAP_CONTENT)

            layoutParams.leftToRight = R.id.progress_start
            layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
            layoutParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
            layoutParams.rightToLeft = R.id.progress_end

            runOnUiThread( Runnable {
                progress.layoutParams = layoutParams
            })

            currentWidth = progressWidth

            if (System.currentTimeMillis() - startTime > 10000) break
        }

        layoutParams = ConstraintLayout.LayoutParams(
            (maxProgressWidth.toInt() + 3), ConstraintLayout.LayoutParams.WRAP_CONTENT)

        layoutParams.leftToRight = R.id.progress_start
        layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
        layoutParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
        layoutParams.rightToLeft = R.id.progress_end

        progress.postDelayed( Runnable {
            progress.layoutParams = layoutParams
        }, 1000L)


        runOnUiThread(Runnable {
            intent = Intent(this@LoadingScreenActivity, ErrorActivity::class.java)
            startActivity(intent)
            finish()
        })

    }
}