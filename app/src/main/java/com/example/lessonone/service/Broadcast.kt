package com.example.lessonone.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast


private const val TAG = "MyBroadcastReceiver"

class Broadcast : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent) {
        val sb = StringBuilder()
        sb.append(
            """
            Action: ${intent.action}
            
            """.trimIndent()
        )
        sb.append(
            """
            URI: ${intent.toUri(Intent.URI_INTENT_SCHEME)}
            
            """.trimIndent()
        )
        val log = sb.toString()
        Toast.makeText(context, log, Toast.LENGTH_LONG).show()
    }
}