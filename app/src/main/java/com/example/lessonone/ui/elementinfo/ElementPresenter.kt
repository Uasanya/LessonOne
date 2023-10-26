package com.example.lessonone.ui.elementinfo

import com.example.lessonone.data.repository.ElementRepository

class ElementPresenter(private val elementRepository: ElementRepository) {

    private var elementView: ElementView? = null

    fun getElementById(id: Int) {
        elementView?.showElement(elementRepository.getDataSourceList()[id])
    }

    fun setCache(id: Int) = elementRepository.setLocalId(id)

    fun onAttach(elementView: ElementView) {
        this.elementView = elementView
    }

    fun onDetach() {
        elementView = null
    }
}