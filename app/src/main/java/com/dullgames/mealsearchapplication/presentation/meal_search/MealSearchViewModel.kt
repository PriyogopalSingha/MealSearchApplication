package com.dullgames.mealsearchapplication.presentation.meal_search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dullgames.mealsearchapplication.common.Resource
import com.dullgames.mealsearchapplication.domain.usecases.MealSearchListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealSearchViewModel @Inject constructor(val mealSearchListUseCase: MealSearchListUseCase) : ViewModel() {

    private val _mealSearchList = MutableStateFlow<MealSearchState>(MealSearchState())
    val mealSearchList : StateFlow<MealSearchState> = _mealSearchList


    fun searchMealInList(s: String){
        viewModelScope.launch(Dispatchers.IO) {
            mealSearchListUseCase(s).onEach {
                when(it){
                    is Resource.Loading -> {
                        _mealSearchList.value = MealSearchState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _mealSearchList.value = MealSearchState(data = it.data)
                    }
                    is Resource.Error -> {
                        _mealSearchList.value = MealSearchState(error=it.message?:"")
                    }
                }
            }
        }
    }
}