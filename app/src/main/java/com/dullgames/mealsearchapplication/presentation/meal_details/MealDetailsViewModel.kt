package com.dullgames.mealsearchapplication.presentation.meal_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dullgames.mealsearchapplication.common.Resource
import com.dullgames.mealsearchapplication.domain.model.MealDetails
import com.dullgames.mealsearchapplication.domain.usecases.MealDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealDetailsViewModel @Inject constructor(private val mealDetailsUseCase: MealDetailsUseCase) :
    ViewModel() {
    private val _mealDetails = MutableStateFlow<MealDetailsState>(MealDetailsState())
    val mealDetails: StateFlow<MealDetailsState> = _mealDetails

    fun getMealDetails(id: String) {
            mealDetailsUseCase(id).onEach {
                when (it) {
                    is Resource.Loading -> {
                        _mealDetails.value = MealDetailsState(isLoading = true)
                    }

                    is Resource.Error -> {
                        _mealDetails.value = MealDetailsState(error = it.message ?: "")
                    }

                    is Resource.Success -> {
                        _mealDetails.value = MealDetailsState(data = it.data?.get(0))
                    }
                }

            }.launchIn(viewModelScope)

        }
    }
