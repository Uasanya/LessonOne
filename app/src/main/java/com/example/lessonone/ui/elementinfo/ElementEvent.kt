package com.example.lessonone.ui.elementinfo

import com.example.lessonone.data.model.Element

sealed interface ElementEvent

class SaveElementEvent(val id: Int) : ElementEvent

object SavedElementEvent : ElementEvent

class LoadElementEvent(val id: Int) : ElementEvent

class LoadedElementEvent(val element: Element) : ElementEvent
