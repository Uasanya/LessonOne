package com.example.lessonone.ui.list

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.lessonone.data.datasource.ElementDataSource
import com.example.lessonone.data.datasource.LocalDataSource
import com.example.lessonone.data.local.Cache
import com.example.lessonone.data.repository.ElementRepository
import com.example.lessonone.databinding.FragmentListBinding
import com.example.lessonone.ui.base.BaseFragment
import com.example.lessonone.ui.elementinfo.ElementFragment
import com.example.lessonone.ui.service.Constant

class ListFragment : BaseFragment<FragmentListBinding>(FragmentListBinding::inflate) {

    private val viewModel: ListViewModel by lazy {
        val preferences: SharedPreferences =
            requireContext().getSharedPreferences(Constant.PREF, Context.MODE_PRIVATE)
        val cache = Cache(preferences)
        val localDataSource = LocalDataSource(cache)
        val elementDataSource = ElementDataSource()
        val repository = ElementRepository(elementDataSource, localDataSource)
        val factory = ListViewModel.provideFactory(repository, this)
        ViewModelProvider(this, factory)[ListViewModel::class.java]
    }
    private val listAdapter: ElementListAdapter by lazy {
        ElementListAdapter {
            navigate(ElementFragment.newInstance(it.id))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.send(LoadEvent())
        viewModel.stateLiveData.observe(viewLifecycleOwner) { state ->
            listAdapter.submitList(state.list)
        }
        binding.rv.adapter = listAdapter
    }
}