package com.example.lessonone.ui.elementinfo

interface ElementEvent

    class SaveEvent(val id : Int) : ElementEvent

    class LoadEvent(val id : Int) : ElementEvent
