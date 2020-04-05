package com.codingwithmitch.daggerpractice.di.auth

import androidx.lifecycle.ViewModel
import com.codingwithmitch.daggerpractice.di.ViewModelKey
import com.codingwithmitch.daggerpractice.ui.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel
}