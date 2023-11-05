package com.example.lessonone.ui.elementinfo

import com.example.lessonone.data.repository.ElementRepository
import com.example.lessonone.ui.base.UseCase

class LoadElementUseCase(private val elementRepository: ElementRepository) :
    UseCase<ElementState, ElementEvent> {
    override fun invoke(state: ElementState, event: ElementEvent): ElementEvent {
        val element = elementRepository.getDataSourceList()[(event as LoadElementEvent).id]
        return LoadedElementEvent(element)
    }

    override fun canHandle(event: ElementEvent): Boolean = event is LoadElementEvent
}