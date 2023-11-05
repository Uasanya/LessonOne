package com.example.lessonone.ui.list

import com.example.lessonone.data.model.Element


interface ListEvent

class LoadListEvent : ListEvent

class LoadedListEvent(val list: List<Element>) : ListEvent