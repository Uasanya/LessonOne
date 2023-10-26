package com.example.lessonone.ui.list

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import com.example.lessonone.data.model.Element
import com.example.lessonone.databinding.FragmentListBinding
import com.example.lessonone.ui.base.BaseFragment
import com.example.lessonone.ui.elementinfo.ElementFragment
import com.example.lessonone.ui.service.Constant

class ListFragment : BaseFragment<FragmentListBinding>(FragmentListBinding::inflate), ListView {


    private var listPresenter: ListPresenter? = null
    private val listAdapter: ElementListAdapter by lazy {
        ElementListAdapter {
            navigate(ElementFragment.newInstance(it.id))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val preferences: SharedPreferences =
            requireContext().getSharedPreferences(Constant.PREF, Context.MODE_PRIVATE)
        listPresenter = ListPresenter(preferences, this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listPresenter?.getList()
        binding.rv.adapter = listAdapter
    }

    override fun showElements(list: List<Element>) {
        listAdapter.submitList(list)
    }

    override fun onDestroy() {
        super.onDestroy()
        listPresenter?.onDestroy()
    }
}