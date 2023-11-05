package com.example.lessonone.ui.list

import com.example.lessonone.data.repository.ElementRepository
import com.example.lessonone.ui.base.UseCase

class LoadListUseCase(private val elementRepository: ElementRepository) :
    UseCase<ListState, ListEvent> {
    override fun invoke(state: ListState, event: ListEvent): ListEvent {
        val list = elementRepository.getDataSourceList()
        return LoadedListEvent(list)
    }

    override fun canHandle(event: ListEvent): Boolean = event is LoadListEvent
}
