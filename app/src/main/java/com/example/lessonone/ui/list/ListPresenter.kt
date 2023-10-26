package com.example.lessonone.ui.list

import android.content.SharedPreferences
import com.example.lessonone.data.repository.ElementRepository

class ListPresenter(preferences: SharedPreferences, private var listView: ListView?) {

    private val elementRepository: ElementRepository = ElementRepository(preferences)


    fun getList() {
        listView?.showElements(elementRepository.getDataSourceList())
    }

    fun onDestroy() {
        listView = null
    }

}