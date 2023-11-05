package com.example.lessonone.ui.list

import com.example.lessonone.ui.base.Reducer

class ListReducer : Reducer<ListState, ListEvent> {
    override fun reduce(state: ListState, event: ListEvent): ListState {
        return when (event) {
            is LoadedListEvent -> state.copy(list = event.list)
            else -> state
        }
    }
}