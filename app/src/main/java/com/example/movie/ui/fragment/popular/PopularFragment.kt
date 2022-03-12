package com.example.movie.ui.fragment.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.movie.R
import com.example.movie.databinding.FragmentPopularBinding

class PopularFragment : Fragment(){

    private var _binding: FragmentPopularBinding? = null
    private val binding: FragmentPopularBinding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_popular, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.movie.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_popularFragment_to_moiveFragment)
        }

        binding.tv.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_popularFragment_to_TVFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        // for prevent memory leaks
        _binding = null
    }
}