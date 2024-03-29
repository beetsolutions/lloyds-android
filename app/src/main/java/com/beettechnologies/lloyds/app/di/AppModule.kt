package com.beettechnologies.lloyds.app.di

import android.content.Context
import com.beettechnologies.lloyds.app.App
import com.beettechnologies.lloyds.app.AppStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext context: Context): App {
        return context as App
    }

    @Singleton
    @Provides
    fun provideAppStorage(@ApplicationContext context: Context): AppStorage {
        return AppStorage(context)
    }
}
