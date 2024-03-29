package com.beettechnologies.lloyds.home.di

import com.beettechnologies.lloyds.home.data.api.MealApi
import com.beettechnologies.lloyds.home.data.repository.MealRepositoryImpl
import com.beettechnologies.lloyds.home.domain.repository.MealRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Singleton
    @Provides
    fun provideCountryApi(retrofit: Retrofit): MealApi {
        return retrofit.create(MealApi::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMealRepository(mealRepository: MealRepositoryImpl): MealRepository
}
