package com.example.lessonone.ui.list

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import com.example.lessonone.data.datasource.ElementDataSource
import com.example.lessonone.data.datasource.LocalDataSource
import com.example.lessonone.data.local.Cache
import com.example.lessonone.data.model.Element
import com.example.lessonone.data.repository.ElementRepository
import com.example.lessonone.databinding.FragmentListBinding
import com.example.lessonone.ui.base.BaseFragment
import com.example.lessonone.ui.elementinfo.ElementFragment
import com.example.lessonone.ui.service.Constant

class ListFragment : BaseFragment<FragmentListBinding>(FragmentListBinding::inflate), ListView {


    private var listPresenter: ListPresenter? = null
    private val listAdapter: ElementListAdapter by lazy {
        ElementListAdapter {
            navigate(ElementFragment.newInstance(it.id))
        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        val preferences: SharedPreferences =
            context.getSharedPreferences(Constant.PREF, Context.MODE_PRIVATE)
        val cache = Cache(preferences)
        val localDataSource = LocalDataSource(cache)
        val elementDataSource = ElementDataSource()
        val repository = ElementRepository(elementDataSource, localDataSource)
        listPresenter = ListPresenter(repository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listPresenter?.onAttach(this)
        listPresenter?.getList()
        binding.rv.adapter = listAdapter
    }

    override fun showElements(list: List<Element>) {
        listAdapter.submitList(list)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        listPresenter?.onDetached()
    }
}