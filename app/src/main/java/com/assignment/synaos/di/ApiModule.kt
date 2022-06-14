package com.assignment.synaos.di

import com.assignment.synaos.data.remote.api.NewsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    fun provideProductApi(retrofit: Retrofit): NewsApiService = retrofit.create(NewsApiService::class.java)
}