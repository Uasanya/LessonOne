package com.example.lessonone.data.datasource

import com.example.lessonone.data.model.Element

class ElementDataSource {

    fun getList(): List<Element> = list

    private val list = List(20) {
        Element(
            id = it,
            name = "name $it",
            description = "description $it"
        )
    }
}