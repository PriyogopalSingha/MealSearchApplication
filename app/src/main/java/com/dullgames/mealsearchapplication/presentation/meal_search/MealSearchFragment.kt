package com.dullgames.mealsearchapplication.presentation.meal_search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.dullgames.mealsearchapplication.R
import com.dullgames.mealsearchapplication.databinding.FragmentMealSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealSearchFragment : Fragment() {

    private var _binding: FragmentMealSearchBinding? = null
    val binding: FragmentMealSearchBinding
        get() = _binding!!
    private val mealSearchAdapter: MealSearchAdapter = MealSearchAdapter()
    private val mealSearchViewModel: MealSearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMealSearchBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.apply {
            this.adapter = mealSearchAdapter
        }
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    mealSearchViewModel.searchMealInList(it)
                }
                return true
            }
        })

        lifecycle.coroutineScope.launchWhenCreated {
            mealSearchViewModel.mealSearchList.collect {
                if (it.isLoading) {
                    binding.notFoundTextview.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE;
                }
                if (it.error.isNotBlank()) {
                    binding.progressBar.visibility = View.GONE
                    binding.notFoundTextview.visibility = View.GONE
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }
                it.data?.let {
                    if (it.isEmpty()) {
                        binding.notFoundTextview.visibility = View.VISIBLE
                    }
                    binding.progressBar.visibility = View.GONE;
                    mealSearchAdapter.setContent(it.toMutableList())
                }
            }
        }
        mealSearchAdapter.itemClickListener {
            findNavController().navigate(
                MealSearchFragmentDirections.actionMealSearchFragmentToMealDetailsFragment(
                    mealId = it.mealId
                )
            )
        }

    }

}