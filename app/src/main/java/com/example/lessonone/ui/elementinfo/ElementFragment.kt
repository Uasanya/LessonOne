package com.example.lessonone.ui.elementinfo

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.lessonone.data.datasource.ElementDataSource
import com.example.lessonone.data.datasource.LocalDataSource
import com.example.lessonone.data.local.Cache
import com.example.lessonone.data.model.Element
import com.example.lessonone.data.repository.ElementRepository
import com.example.lessonone.databinding.FragmentElementBinding
import com.example.lessonone.ui.base.BaseFragment
import com.example.lessonone.ui.service.Constant


class ElementFragment : BaseFragment<FragmentElementBinding>(FragmentElementBinding::inflate),
    ElementView {


    private var elementPresenter: ElementPresenter? = null
    private var id: Int? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val preferences: SharedPreferences =
            context.getSharedPreferences(Constant.PREF, Context.MODE_PRIVATE)
        val cache = Cache(preferences)
        val localDataSource = LocalDataSource(cache)
        val elementDataSource = ElementDataSource()
        val repository = ElementRepository(elementDataSource, localDataSource)
        elementPresenter = ElementPresenter(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        id = arguments?.getInt(ARG_PARAM1).apply {
            this?.let { elementPresenter?.setCache(this) }
        } ?: return
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        elementPresenter?.onAttach(this)
        elementPresenter?.getElementById(id!!)
    }

    override fun showElement(element: Element) {
        binding.tvId.text = element.id.toString()
        binding.tvName.text = element.name
        binding.tvDesc.text = element.description
    }

    override fun onDestroyView() {
        super.onDestroyView()
        elementPresenter?.onDetach()
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

}