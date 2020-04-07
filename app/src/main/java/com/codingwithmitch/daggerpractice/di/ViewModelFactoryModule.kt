package com.codingwithmitch.daggerpractice.di

import androidx.lifecycle.ViewModelProvider
import com.codingwithmitch.daggerpractice.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}