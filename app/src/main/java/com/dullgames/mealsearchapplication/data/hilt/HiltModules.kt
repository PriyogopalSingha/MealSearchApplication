package com.dullgames.mealsearchapplication.data.hilt

import com.dullgames.mealsearchapplication.common.Constants
import com.dullgames.mealsearchapplication.data.remote.MealSearchAPI
import com.dullgames.mealsearchapplication.data.repository.MealDetailsRepositoryImpl
import com.dullgames.mealsearchapplication.data.repository.MealSearchRepositoryImpl
import com.dullgames.mealsearchapplication.domain.repository.MealDetailsRepository
import com.dullgames.mealsearchapplication.domain.repository.MealSearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModules {

    @Provides
    @Singleton
    fun provideMealSearchAPI(): MealSearchAPI {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(MealSearchAPI::class.java)
    }

    @Provides
    fun provideMealSearchRepository(mealSearchAPI: MealSearchAPI): MealSearchRepository {
        return MealSearchRepositoryImpl(mealSearchAPI)
    }

    @Provides
    fun provideMealDetailsRepository(mealSearchAPI: MealSearchAPI): MealDetailsRepository {
        return MealDetailsRepositoryImpl(mealSearchAPI)
    }

}