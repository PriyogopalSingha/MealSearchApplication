package com.dullgames.mealsearchapplication.data.repository

import com.dullgames.mealsearchapplication.data.model.MealsDTO
import com.dullgames.mealsearchapplication.data.remote.MealSearchAPI
import com.dullgames.mealsearchapplication.domain.repository.MealSearchRepository

class MealSearchRepositoryImpl(private val mealSearchAPI: MealSearchAPI): MealSearchRepository {
    override suspend fun getMealSearchList(s: String): MealsDTO {
        return mealSearchAPI.getMealList(s)
    }
}