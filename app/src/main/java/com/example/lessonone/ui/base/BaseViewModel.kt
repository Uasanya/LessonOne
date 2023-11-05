package com.example.lessonone.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lessonone.ui.base.Reducer
import com.example.lessonone.ui.base.UseCase

abstract class BaseViewModel<State, Event>(
    private val useCases: List<UseCase<State, Event>>,
    private val reducer: Reducer<State, Event>,
    initialState: State
) : ViewModel() {

    private val _state = MutableLiveData(initialState)
    val state: LiveData<State> = _state

    protected fun sendEvent(event: Event) {
        Log.i("EVENT", event.toString())
        val newState = reducer.reduce(
            state = _state.value ?: throw IllegalStateException("No state set"),
            event = event
        )
        _state.value = newState
        useCases.filter { it.canHandle(event) }.forEach { useCase ->
            val result = useCase.invoke(newState, event)
            sendEvent(event = result)
        }
    }
}