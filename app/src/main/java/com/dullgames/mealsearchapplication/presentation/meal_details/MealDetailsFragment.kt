package com.dullgames.mealsearchapplication.presentation.meal_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dullgames.mealsearchapplication.R
import com.dullgames.mealsearchapplication.databinding.FragmentMealDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealDetailsFragment : Fragment() {

    private var _binding: FragmentMealDetailsBinding? = null
    val binding: FragmentMealDetailsBinding
        get() = _binding!!

    private val args: MealDetailsFragmentArgs by navArgs()
    private val viewModel: MealDetailsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMealDetailsBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args.mealId?.let {
            viewModel.getMealDetails(it)
        }
        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.mealDetails.collect{
                if(it.isLoading){
                    binding.progressBarDetailsPage.visibility = View.VISIBLE
                }
                if(it.error.isNotBlank()){
                    binding.progressBarDetailsPage.visibility = View.GONE
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }
                it.data?.let{
                    binding.progressBarDetailsPage.visibility = View.GONE
                    binding.mealDetails = it
                }
            }
        }
        binding.backArrow.setOnClickListener {
            findNavController().popBackStack()
        }

    }


}