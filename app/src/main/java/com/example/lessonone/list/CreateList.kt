package com.example.lessonone.list

import com.example.lessonone.element.Element

class CreateList {

    companion object {
        fun create(): List<Element> {
            return List(20) {
                Element(
                    id = it,
                    name = "name $it",
                    description = "description $it"
                )
            }
        }
    }
}
