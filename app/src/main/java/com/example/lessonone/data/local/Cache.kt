package com.example.lessonone.data.local

import android.content.SharedPreferences
import androidx.core.content.edit

class Cache(private val preferences: SharedPreferences) {


    fun putId(id: Int) {
        preferences.edit {
            putInt(PREF_KEY_ID, id)
            apply()
        }
    }

    fun getId(): Int {
        return preferences.getInt(PREF_KEY_ID, -1)
    }

    companion object {
        private const val PREF_KEY_ID = "PREF_KEY_ID"
    }
}

