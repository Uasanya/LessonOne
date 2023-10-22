package com.example.lessonone.cache

import android.content.Context
import android.content.SharedPreferences

class Cache(context: Context) {

    companion object {
        private const val PREF: String = "PREF_FILE"
        private const val PREF_KEY_ID = "PREF_KEY_ID"
    }

    private val preferences: SharedPreferences =
        context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = preferences.edit()

    fun putId(id: Int) {
        editor.putInt(PREF_KEY_ID, id)
        editor.apply()
    }

    fun getId(): Int {
        return preferences.getInt(PREF_KEY_ID,-1)
    }
}

