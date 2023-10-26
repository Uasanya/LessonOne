package com.example.lessonone.data.repository

import android.content.SharedPreferences
import com.example.lessonone.data.datasource.ElementDataSource
import com.example.lessonone.data.datasource.LocalDataSource
import com.example.lessonone.data.model.Element

class ElementRepository(preferences: SharedPreferences) {

    private val elementDataSource: ElementDataSource = ElementDataSource()
    private val localDataSource: LocalDataSource = LocalDataSource(preferences)

    fun setLocalId(id: Int) = localDataSource.setElementId(id)

    fun getLocalId(): Int = localDataSource.getElementId()

    fun getDataSourceList(): List<Element> = elementDataSource.getList()

}