package com.example.lessonone.data.datasource

import android.content.SharedPreferences
import com.example.lessonone.data.local.Cache

class LocalDataSource(preferences: SharedPreferences) {

    private val cache: Cache = Cache(preferences)

    fun getElementId(): Int = cache.getId()

    fun setElementId(id: Int) = cache.putId(id)
}