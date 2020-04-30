package com.codingwithmitch.daggerpractice.di.main

import androidx.lifecycle.ViewModel
import com.codingwithmitch.daggerpractice.di.ViewModelKey
import com.codingwithmitch.daggerpractice.ui.main.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel
}