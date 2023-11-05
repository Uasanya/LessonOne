package com.example.lessonone.ui.elementinfo

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.lessonone.base.BaseViewModel
import com.example.lessonone.ui.base.UseCase

class ElementViewModel(
    useCases: List<UseCase<ElementState, ElementEvent>>
) :
    BaseViewModel<ElementState, ElementEvent>(
        useCases = useCases,
        reducer = ElementReducer(),
        initialState = ElementState(null)
    ) {

    fun getElement(id: Int) {
        sendEvent(LoadElementEvent(id))
    }

    fun setCache(id: Int) {
        sendEvent(SaveElementEvent(id))
    }

    companion object {
        fun provideFactory(
            useCases: List<UseCase<ElementState, ElementEvent>>,
            owner: SavedStateRegistryOwner,
            defaultArgs: Bundle? = null,
        ): AbstractSavedStateViewModelFactory =
            object : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(
                    key: String,
                    modelClass: Class<T>,
                    handle: SavedStateHandle
                ): T {
                    return ElementViewModel(useCases) as T
                }
            }
    }
}