package com.example.lessonone.ui.elementinfo

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.lessonone.data.model.Element
import com.example.lessonone.databinding.FragmentElementBinding
import com.example.lessonone.ui.base.BaseFragment
import com.example.lessonone.ui.service.Constant


class ElementFragment : BaseFragment<FragmentElementBinding>(FragmentElementBinding::inflate),
    ElementView {

    private var elem: Element? = null
    private var elementPresenter: ElementPresenter? = null
    private var id: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val preferences: SharedPreferences =
            requireContext().getSharedPreferences(Constant.PREF, Context.MODE_PRIVATE)
        elementPresenter = ElementPresenter(preferences, this)
        id = arguments?.getInt(ARG_PARAM1).apply {
            this.let { elementPresenter?.setCache(this!!) }
        } ?: return
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        elementPresenter?.getElementById(id!!)
        binding.tvId.text = elem?.id.toString()
        binding.tvName.text = elem?.name
        binding.tvDesc.text = elem?.description
    }

    companion object {
        private const val ARG_PARAM1: String = "key"

        fun newInstance(id: Int): Fragment {
            val elementFragment = ElementFragment()
            val args = Bundle()
            args.putInt(ARG_PARAM1, id)
            elementFragment.arguments = args
            return elementFragment
        }
    }

    override fun showElement(element: Element) {
        elem = element
    }

    override fun onDestroy() {
        super.onDestroy()
        elementPresenter?.onDestroy()
    }

}