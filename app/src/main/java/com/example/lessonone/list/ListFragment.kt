package com.example.lessonone.list

import android.os.Bundle
import android.view.View
import com.example.lessonone.base.BaseFragment
import com.example.lessonone.databinding.FragmentListBinding
import com.example.lessonone.element.Element
import com.example.lessonone.element.ElementFragment

class ListFragment : BaseFragment<FragmentListBinding>(FragmentListBinding::inflate),
    ElementListener {

    private var listAdapter: ElementListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listAdapter = ElementListAdapter {
            navigateToElement(it)
        }.apply { submitList(ElementList.list) }
        binding.rv.adapter = listAdapter
    }

    override fun navigateToElement(element: Element) {
        navigate(ElementFragment.newInstance(element))
    }
}