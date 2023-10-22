package com.example.lessonone.list

import com.example.lessonone.element.Element

object ElementList {

    val list = List(20) {
            Element(
                id = it,
                name = "name $it",
                description = "description $it"
            )
        }

    fun getElementById(id: Int): Element {
        return list[id]
    }
}

