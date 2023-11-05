package com.example.lessonone.ui.list

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.lessonone.base.BaseViewModel
import com.example.lessonone.ui.base.UseCase

class ListViewModel(
    useCases: List<UseCase<ListState, ListEvent>>
) : BaseViewModel<ListState, ListEvent>(
    useCases = useCases,
    reducer = ListReducer(),
    initialState = ListState(emptyList())
) {

    fun getList() {
        sendEvent(LoadListEvent())
    }

    companion object {

        fun provideFactory(
            useCases: List<UseCase<ListState, ListEvent>>,
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
                    return ListViewModel(useCases) as T
                }
            }
    }
}
