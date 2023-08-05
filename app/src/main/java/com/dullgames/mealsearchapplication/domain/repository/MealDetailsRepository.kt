package com.dullgames.mealsearchapplication.domain.repository

import com.dullgames.mealsearchapplication.data.model.MealsDTO

interface MealDetailsRepository {
    suspend fun getMealDetails(id: String): MealsDTO
}