package com.example.lessonone.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.lessonone.MainActivity
import com.example.lessonone.cache.Cache

class MyBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Constant.ID_ACTION) {
            val cache = Cache(context)
            val id = cache.getId()
            val idIntent = Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                putExtra(Constant.KEY_ID, id)
            }
         context.startActivity(idIntent)
        }
    }
}