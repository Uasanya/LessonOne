package com.example.lessonone.ui.elementinfo

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.lessonone.data.datasource.ElementDataSource
import com.example.lessonone.data.datasource.LocalDataSource
import com.example.lessonone.data.local.Cache
import com.example.lessonone.data.repository.ElementRepository
import com.example.lessonone.databinding.FragmentElementBinding
import com.example.lessonone.ui.base.BaseFragment
import com.example.lessonone.ui.service.Constant


class ElementFragment : BaseFragment<FragmentElementBinding>(FragmentElementBinding::inflate) {

    private var id: Int? = null
    private var viewModel: ElementViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val preferences: SharedPreferences =
            requireContext().getSharedPreferences(Constant.PREF, Context.MODE_PRIVATE)
        val cache = Cache(preferences)
        val localDataSource = LocalDataSource(cache)
        val elementDataSource = ElementDataSource()
        val repository = ElementRepository(elementDataSource, localDataSource)
        val factory = ElementViewModel.provideFactory(repository, this)
        viewModel = ViewModelProvider(this, factory)[ElementViewModel::class.java]
        id = arguments?.getInt(ARG_PARAM1).apply {
            this?.let { viewModel?.setCache(this) }
        } ?: return

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel?.getElement(id!!)
        viewModel?.elementLiveData?.observe(viewLifecycleOwner) { element ->
            binding.tvId.text = element.id.toString()
            binding.tvName.text = element.name
            binding.tvDesc.text = element.description
        }
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
