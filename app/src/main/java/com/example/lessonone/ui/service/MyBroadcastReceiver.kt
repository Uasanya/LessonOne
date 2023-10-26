package com.example.lessonone.ui.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.example.lessonone.ui.MainActivity
import com.example.lessonone.ui.elementinfo.ElementPresenter
import com.example.lessonone.ui.elementinfo.ElementView

class MyBroadcastReceiver : BroadcastReceiver(), ElementView {

    private var elementPresenter: ElementPresenter? = null

    override fun onReceive(context: Context, intent: Intent) {
        val preferences: SharedPreferences =
            context.getSharedPreferences(Constant.PREF, Context.MODE_PRIVATE)
        elementPresenter = ElementPresenter(preferences, this)
        if (intent.action == Constant.ID_ACTION) {
            val id = elementPresenter?.getCache()
            val idIntent = Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                putExtra(Constant.KEY_ID, id)
            }
            context.startActivity(idIntent)
        }
    }
}