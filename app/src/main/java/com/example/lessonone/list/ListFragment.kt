package com.example.lessonone.list

import android.os.Bundle
import android.view.View
import com.example.lessonone.base.BaseFragment
import com.example.lessonone.databinding.FragmentListBinding
import com.example.lessonone.element.ElementFragment

class ListFragment : BaseFragment<FragmentListBinding>(FragmentListBinding::inflate) {

    private val listAdapter: ElementListAdapter by lazy {
        ElementListAdapter {
            navigate(ElementFragment.newInstance(it.id))
        }.apply { submitList(ElementList.list) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rv.adapter = listAdapter
    }
}