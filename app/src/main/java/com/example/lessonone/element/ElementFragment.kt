package com.example.lessonone.element

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.lessonone.base.BaseFragment
import com.example.lessonone.cache.Cache
import com.example.lessonone.databinding.FragmentElementBinding
import com.example.lessonone.list.ElementList
import com.example.lessonone.service.Constant


class ElementFragment : BaseFragment<FragmentElementBinding>(FragmentElementBinding::inflate) {

    private var elem: Element? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val preferences:SharedPreferences =
            requireContext().getSharedPreferences(Constant.PREF, Context.MODE_PRIVATE)
        val cache = Cache(preferences)
        val id = arguments?.getInt(ARG_PARAM1).apply {
            this?.let { cache.putId(it) }
        }?: return
        elem = ElementList.getElementById(id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvId.text = elem?.id.toString()
        binding.tvName.text = elem?.name
        binding.tvDesc.text = elem?.description
    }

    companion object {
        private const val ARG_PARAM1: String = "key"

        fun newInstance(id:Int): Fragment {
            val elementFragment = ElementFragment()
            val args = Bundle()
            args.putInt(ARG_PARAM1, id)
            elementFragment.arguments = args
            return elementFragment
        }
    }

}