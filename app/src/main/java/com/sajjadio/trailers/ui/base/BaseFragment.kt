package com.sajjadio.trailers.ui.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sajjadio.trailers.BR
import com.sajjadio.trailers.R

abstract class BaseFragment<VDB : ViewDataBinding, VM : ViewModel>(@LayoutRes private val layoutId: Int) :
    Fragment() {

    abstract val LOG_TAG: String
    abstract val viewModelClass: Class<VM>
    protected val viewModel: VM by lazy {
        ViewModelProvider(this)[viewModelClass]
    }

    private lateinit var _binding: VDB
    protected val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        _binding.apply {
            setHasOptionsMenu(true)
            setVariable(BR.viewModel, viewModel)
            lifecycleOwner = viewLifecycleOwner
            return root
        }
    }

    private fun changeStatusBarColor() {
        when (layoutId) {
            R.layout.fragment_home -> {
                setColorOfStatusBar(R.color.transparent_color, false)
            }
            R.layout.fragment_details -> {
                setColorOfStatusBar(R.color.transparent_color, false)
            }
            else -> {
                setColorOfStatusBar(R.color.secondary_color, true)
            }
        }
    }

    private fun setColorOfStatusBar(color: Int, show: Boolean) {
        activity?.window?.let { window ->
            WindowCompat.setDecorFitsSystemWindows(window, show)
            window.statusBarColor = ContextCompat.getColor(requireContext(), color)
        }
    }

    protected fun log(value: String) {
        Log.v(LOG_TAG, value)
    }
}