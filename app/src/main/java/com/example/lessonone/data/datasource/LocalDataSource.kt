package com.example.lessonone.data.datasource

import com.example.lessonone.data.local.Cache

class LocalDataSource(private val cache: Cache) {

    fun setElementId(id: Int) = cache.putId(id)
}