package com.dullgames.mealsearchapplication.presentation.meal_details

import com.dullgames.mealsearchapplication.domain.model.MealDetails

data class MealDetailsState(
    val data: MealDetails? = null,
    val error: String = "",
    val isLoading: Boolean = false
)