package com.example.lessonone.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewbinding.ViewBinding
import com.example.lessonone.R

abstract class BaseFragment<VB : ViewBinding>(
    private val inflate: (inflater: LayoutInflater) -> VB
) : Fragment() {

    private var _binding: VB? = null
    val binding get() = _binding!!
    private var fm: FragmentManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fm = requireActivity().supportFragmentManager
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater)
        return binding.root
    }

    protected fun navigate(fragment: Fragment) {
        val ft: FragmentTransaction? = fm?.beginTransaction()
        ft?.replace(R.id.container, fragment, fragment.tag)?.addToBackStack(null)?.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}