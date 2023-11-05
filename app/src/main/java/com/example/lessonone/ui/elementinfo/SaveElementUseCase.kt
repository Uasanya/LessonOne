package com.example.lessonone.ui.elementinfo

import com.example.lessonone.data.repository.ElementRepository
import com.example.lessonone.ui.base.UseCase

class SaveElementUseCase(private val elementRepository: ElementRepository) :
    UseCase<ElementState, ElementEvent> {
    override fun invoke(state: ElementState, event: ElementEvent): ElementEvent {
        elementRepository.setLocalId((event as SaveElementEvent).id)
        return SavedElementEvent
    }

    override fun canHandle(event: ElementEvent): Boolean = event is SaveElementEvent
}