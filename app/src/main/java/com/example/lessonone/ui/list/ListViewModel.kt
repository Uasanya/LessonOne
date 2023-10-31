package com.example.lessonone.ui.list

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.lessonone.data.repository.ElementRepository

class ListViewModel(
    private val elementRepository: ElementRepository
) : ViewModel() {

    private val _stateLiveData = MutableLiveData<ListState>()
    val stateLiveData: LiveData<ListState>
        get() = _stateLiveData

    private fun getList() {
        val list = elementRepository.getDataSourceList()
        _stateLiveData.value = ListState(list)
    }

    fun send(event: ListEvent) {
        when (event) {
            is LoadEvent -> getList()
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
                    return ListViewModel(elementRepository) as T
                }
            }
    }
}
