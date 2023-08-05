package com.dullgames.mealsearchapplication.domain.repository

import com.dullgames.mealsearchapplication.data.model.MealsDTO

interface MealSearchRepository {
    suspend fun getMealSearchList(s: String): MealsDTO
}