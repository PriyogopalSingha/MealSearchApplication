package com.dullgames.mealsearchapplication.data.repository

import com.dullgames.mealsearchapplication.data.model.MealsDTO
import com.dullgames.mealsearchapplication.data.remote.MealSearchAPI
import com.dullgames.mealsearchapplication.domain.repository.MealDetailsRepository

class MealDetailsRepositoryImpl(private val mealSearchAPI: MealSearchAPI): MealDetailsRepository {
    override suspend fun getMealDetails(id: String): MealsDTO {
        return mealSearchAPI.getMealDetails(id)
    }
}