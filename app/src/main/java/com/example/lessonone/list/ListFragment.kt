package com.example.lessonone.list

import android.os.Bundle
import android.view.View
import com.example.lessonone.base.BaseFragment
import com.example.lessonone.databinding.FragmentListBinding
import com.example.lessonone.element.ElementFragment

class ListFragment : BaseFragment<FragmentListBinding>(FragmentListBinding::inflate) {

    private var listAdapter: ElementListAdapter? = null

    // private val listAdapter: ElementListAdapter by lazy { navigate() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listAdapter = ElementListAdapter {
            navigate(ElementFragment.newInstance(it))
        }.apply { submitList(ElementList.list) }
        binding.rv.adapter = listAdapter
    }
}