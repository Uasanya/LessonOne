package com.example.lessonone.data.repository

import com.example.lessonone.data.datasource.ElementDataSource
import com.example.lessonone.data.datasource.LocalDataSource
import com.example.lessonone.data.model.Element

class ElementRepository(
    private val elementDataSource: ElementDataSource,
    private val localDataSource: LocalDataSource
) {

    fun setLocalId(id: Int) = localDataSource.setElementId(id)

    fun getDataSourceList(): List<Element> = elementDataSource.getList()

}