package com.example.lessonone.ui.list

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import com.example.lessonone.data.model.Element
import com.example.lessonone.data.repository.ElementRepository
import kotlinx.coroutines.launch

class ListViewModel(
    private val elementRepository: ElementRepository
) : ViewModel() {

    private val _listLiveData = MutableLiveData<List<Element>>()
    val listLiveData: LiveData<List<Element>>
        get() = _listLiveData

    fun getList() {
        val list = elementRepository.getDataSourceList()
        _listLiveData.value = list
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
