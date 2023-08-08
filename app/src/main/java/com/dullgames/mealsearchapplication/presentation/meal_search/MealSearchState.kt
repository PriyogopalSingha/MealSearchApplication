package com.dullgames.mealsearchapplication.presentation.meal_search

import com.dullgames.mealsearchapplication.domain.model.Meal

data class MealSearchState(
    val data: List<Meal>? = null,
    val error: String = "",
    val isLoading: Boolean = false
)
