package com.example.lessonone.ui.base

interface Reducer<State, Event> {
    fun reduce(state: State, event: Event): State
}