package com.assignment.synaos.di

import com.assignment.synaos.data.repository.RepositoryImp
import com.assignment.synaos.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindRepository(repositoryImp: RepositoryImp): Repository
}