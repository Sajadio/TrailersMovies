package com.example.movie.ui.fragment.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.movie.R
import com.example.movie.databinding.FragmentHomeBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.movie.data.model.Category
import com.example.movie.data.model.Trend
import com.example.movie.data.repository.Repository
import com.example.movie.ui.fragment.home.adapter.HomeAdapter
import com.example.movie.ui.fragment.home.adapter.OnClickListener
import android.widget.ArrayAdapter








class HomeFragment : Fragment(), OnClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.toolbar.title = ""
        binding.toolbar.subtitle = ""
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)
        setHasOptionsMenu(true)

        binding.btnSearch.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }

        val adapter = HomeAdapter(Repository.getAllItems(), lifecycle, this)
        binding.parentRecyclerView.adapter = adapter
        binding.parentRecyclerView.setHasFixedSize(true)

        binding.btnMenu.setOnClickListener {
            showDialog(view)
        }

    }

    private fun showDialog(view: View) {

        val builderSingle = AlertDialog.Builder(requireContext())
        val arrayAdapter = ArrayAdapter<String>(requireContext(), android.R.layout.select_dialog_singlechoice)
        arrayAdapter.add(resources.getString(R.string.settings))
        arrayAdapter.add(resources.getString(R.string.movie))
        arrayAdapter.add(resources.getString(R.string.series))

        builderSingle.setAdapter(arrayAdapter) { _, which ->
            when(arrayAdapter.getItem(which)){
                resources.getString(R.string.settings) -> {view.findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)}
                resources.getString(R.string.movie) -> {view.findNavController().navigate(R.id.action_homeFragment_to_moiveFragment)}
                resources.getString(R.string.series) -> {view.findNavController().navigate(R.id.action_homeFragment_to_seriesFragment)}
            }
        }
        builderSingle.show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        // for prevent memory leaks
        _binding = null
    }


    override fun trendItem(trend: Trend) {
        Toast.makeText(requireContext(), "trend", Toast.LENGTH_SHORT).show()
    }

    override fun category(category: Int) {
        Toast.makeText(requireContext(), "categoryMove", Toast.LENGTH_SHORT).show()
    }

    override fun openItem(category: Category) {
        Toast.makeText(requireContext(), "view more category", Toast.LENGTH_SHORT).show()
    }

    override fun popular(popular: Int) {
        Toast.makeText(requireContext(), "popularMove", Toast.LENGTH_SHORT).show()
    }


}