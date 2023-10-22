package com.example.lessonone.element

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.lessonone.base.BaseFragment
import com.example.lessonone.cache.Cache
import com.example.lessonone.databinding.FragmentElementBinding


class ElementFragment : BaseFragment<FragmentElementBinding>(FragmentElementBinding::inflate) {

    private var elem: Element? = null

    companion object {
        private const val ARG_PARAM1: String = "key"

        fun newInstance(element: Element): Fragment {
            val elementFragment = ElementFragment()
            val args = Bundle()
            args.putParcelable(ARG_PARAM1, element)
            elementFragment.arguments = args
            return elementFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cache = Cache(requireContext())
        elem = arguments?.getParcelable<Element>(ARG_PARAM1).apply {
            this?.let { cache.putId(it.id) }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvId.text = elem?.id.toString()
        binding.tvName.text = elem?.name
        binding.tvDesc.text = elem?.description
    }

}