package com.example.lessonone.ui.elementinfo

import com.example.lessonone.ui.base.Reducer

class ElementReducer : Reducer<ElementState, ElementEvent> {
    override fun reduce(state: ElementState, event: ElementEvent): ElementState {
        return when (event) {
            is LoadedElementEvent -> state.copy(element = event.element)
            else -> state
        }
    }
}