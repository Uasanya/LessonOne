package com.example.lessonone.ui.list

import com.example.lessonone.data.repository.ElementRepository

class ListPresenter(private val elementRepository: ElementRepository) {

    private var listView: ListView? = null

    fun getList() {
        listView?.showElements(elementRepository.getDataSourceList())
    }

    fun onAttach(listView: ListView) {
        this.listView = listView
    }

    fun onDetached() {
        listView = null
    }
}