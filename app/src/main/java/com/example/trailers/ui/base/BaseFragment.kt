package com.example.trailers.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<DB : ViewDataBinding>(@LayoutRes private val layoutId: Int) :
    Fragment() {

    private var _binding: DB? = null
    val binding: DB get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        setHasOptionsMenu(true)
        initial()
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this.viewLifecycleOwner
    }

    abstract fun initial()

    override fun onDestroy() {
        super.onDestroy()
        // for prevent memory leaks
        _binding = null
    }

}