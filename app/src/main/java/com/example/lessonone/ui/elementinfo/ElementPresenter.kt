package com.example.lessonone.ui.elementinfo

import android.content.SharedPreferences
import com.example.lessonone.data.repository.ElementRepository

class ElementPresenter(preferences: SharedPreferences, private var elementView: ElementView?) :
    ElementView {

    private val elementRepository: ElementRepository = ElementRepository(preferences)

    fun getElementById(id: Int) {
        elementView?.showElement(elementRepository.getDataSourceList()[id])
    }

    fun setCache(id: Int) = elementRepository.setLocalId(id)

    fun getCache(): Int = elementRepository.getLocalId()

    fun onDestroy() {
        elementView = null
    }
}