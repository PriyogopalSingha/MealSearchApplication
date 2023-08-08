package com.dullgames.mealsearchapplication.domain.usecases

import com.dullgames.mealsearchapplication.common.Resource
import com.dullgames.mealsearchapplication.data.model.toDomainMeal
import com.dullgames.mealsearchapplication.domain.model.Meal
import com.dullgames.mealsearchapplication.domain.repository.MealSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MealSearchListUseCase @Inject constructor(private val repository: MealSearchRepository) {

    operator fun invoke(s: String): Flow<Resource<List<Meal>>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getMealSearchList(s)
            val list =
                if (response.meals.isNullOrEmpty()) emptyList() else response.meals.map { it -> it.toDomainMeal() }
            emit(Resource.Success(data = list))

        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Check for Internet Connection"))
        } catch (e: Exception) {
        }
    }
}