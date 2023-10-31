package com.example.lessonone.ui.elementinfo

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.lessonone.data.repository.ElementRepository

class ElementViewModel(private val elementRepository: ElementRepository) : ViewModel() {


    private val _stateLiveData = MutableLiveData<ElementState>()
    val stateLiveData: LiveData<ElementState>
        get() = _stateLiveData


    private fun getElement(id: Int) {
        val element = elementRepository.getDataSourceList()[id]
        _stateLiveData.value = ElementState(element)
    }

    private fun setCache(id: Int) {
        elementRepository.setLocalId(id)
    }

    fun send(event: ElementEvent) {
        when (event) {
            is SaveEvent -> setCache(event.id)

            is LoadEvent -> getElement(event.id)
        }
    }

    companion object {
        fun provideFactory(
            elementRepository: ElementRepository,
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
                    return ElementViewModel(elementRepository) as T
                }
            }
    }
}