package com.example.lessonone

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.lessonone.element.ElementFragment
import com.example.lessonone.list.ElementList
import com.example.lessonone.service.Constant
import com.example.lessonone.service.ForegroundService


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intent?.extras?.let { bundle ->
            if (bundle.containsKey(Constant.KEY_ID)) {
                val id = intent.getIntExtra(Constant.KEY_ID, -1)
                if (id >= 0) {
                    val elem = ElementList.getElementById(id)
                    navigate(ElementFragment.newInstance(elem))
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
