package com.example.lessonone.ui.base

interface UseCase<State, Event> {
    fun invoke(state: State, event: Event): Event
    fun canHandle(event: Event): Boolean
}