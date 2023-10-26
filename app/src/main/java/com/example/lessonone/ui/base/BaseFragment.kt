package com.example.lessonone.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.lessonone.ui.MainActivity

abstract class BaseFragment<VB : ViewBinding>(
    private val inflate: (inflater: LayoutInflater) -> VB
) : Fragment() {

    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater)
        return binding.root
    }

    protected fun navigate(fragment: Fragment) {
        (requireActivity() as MainActivity).navigate(fragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}