package com.codingwithmitch.daggerpractice.di

import androidx.lifecycle.ViewModelProvider
import com.codingwithmitch.daggerpractice.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {
    @Binds
    fun bindViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}