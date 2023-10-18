package com.example.lessonone.list

import com.example.lessonone.element.Element

class CreateList {

    companion object {
        fun create(): List<Element> {
            val elements: MutableList<Element> = mutableListOf()
            for (i in 0..19) {
                elements.add(
                    Element(
                    id = i,
                    name = "name $i",
                    description = "description $i"
                ))
            }
            return elements
        }
    }
}