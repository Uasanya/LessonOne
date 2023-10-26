package com.example.lessonone.ui

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.lessonone.R
import com.example.lessonone.ui.elementinfo.ElementFragment
import com.example.lessonone.ui.service.Constant
import com.example.lessonone.ui.service.ForegroundService


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intent?.extras?.let { bundle ->
            if (bundle.containsKey(Constant.KEY_ID)) {
                val id = intent.getIntExtra(Constant.KEY_ID, -1)
                if (id >= 0) {
                    navigate(ElementFragment.newInstance(id))
                }
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                0
            )
        }

        val serviceIntent = Intent(this, ForegroundService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent)
        } else {
            startService(serviceIntent)
        }
    }

    fun navigate(fragment: Fragment) {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.container, fragment, fragment.tag).addToBackStack(null).commit()
    }
}
