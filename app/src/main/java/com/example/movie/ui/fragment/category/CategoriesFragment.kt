package com.example.movie.ui.fragment.category

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.movie.R
import com.example.movie.databinding.FragmentCategoriesBinding

class CategoriesFragment: Fragment() {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding: FragmentCategoriesBinding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_categories, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)
        binding.toolbar.title = ""
        binding.toolbar.subtitle = ""
        binding.titleToolbar.text = "Categories"
        setHasOptionsMenu(true)

        binding.movie.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_categoriesFragment_to_moiveFragment)
        }

        binding.tv.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_categoriesFragment_to_seriesFragment)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24)
        binding.toolbar.setNavigationOnClickListener { activity!!.onBackPressed() }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // for prevent memory leaks
        _binding = null
    }

}