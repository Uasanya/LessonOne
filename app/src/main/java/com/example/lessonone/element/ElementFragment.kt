package com.example.lessonone.element

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.lessonone.base.BaseFragment
import com.example.lessonone.cache.Cache
import com.example.lessonone.databinding.FragmentElementBinding


class ElementFragment : BaseFragment<FragmentElementBinding>(FragmentElementBinding::inflate) {

    private var elem: Element? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val preferences: SharedPreferences =
            requireContext().getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val cache = Cache(preferences)
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

    companion object {
        private const val ARG_PARAM1: String = "key"
        private const val PREF: String = "PREF_FILE"

        fun newInstance(element: Element): Fragment {
            val elementFragment = ElementFragment()
            val args = Bundle()
            args.putParcelable(ARG_PARAM1, element)
            elementFragment.arguments = args
            return elementFragment
        }
    }

}