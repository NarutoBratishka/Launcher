package com.mentalscapes

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class AutorizationActivity : AppCompatActivity() {
    lateinit var loginEdit: EditText
    lateinit var passwordEdit: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_autorization)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        supportActionBar?.hide()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE or
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

        loginEdit = findViewById(R.id.login_field)
        passwordEdit = findViewById(R.id.password_field)
    }

    fun logIn(v: View) {
//        Toast.makeText(this, "Логин: ${loginEdit.text}\nПароль: ${passwordEdit.text}", Toast.LENGTH_SHORT).show()
        intent = Intent(this, ErrorActivity::class.java)
        startActivity(intent)
    }
}