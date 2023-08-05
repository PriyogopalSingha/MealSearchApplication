package com.dullgames.mealsearchapplication.domain.usecases

import com.dullgames.mealsearchapplication.common.Resource
import com.dullgames.mealsearchapplication.data.model.toDomainMealDetails
import com.dullgames.mealsearchapplication.domain.model.MealDetails
import com.dullgames.mealsearchapplication.domain.repository.MealDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MealDetailsUseCase @Inject constructor(private val repository: MealDetailsRepository) {
    operator fun invoke(id: String): Flow<Resource<MealDetails>> = flow {
        try {
            emit(Resource.Loading())

            val response = repository.getMealDetails(id).meals[0].toDomainMealDetails()
            emit(Resource.Success(response))

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Check for Internet Connection"))
        } catch (e: Exception) {
        }
    }
}
