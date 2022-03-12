package com.example.movie.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.movie.R
import com.example.movie.databinding.FragmentHomeBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.topAppBar)

        binding.movie.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_homeFragment_to_moiveFragment)
        }

        binding.tv.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_homeFragment_to_TVFragment)
        }

        binding.category.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_homeFragment_to_categoriesFragment)
        }

        binding.popular.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_homeFragment_to_popularFragment)
        }

        binding.search.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }

        binding.settings.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
        }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        // for prevent memory leaks
        _binding = null
    }

}