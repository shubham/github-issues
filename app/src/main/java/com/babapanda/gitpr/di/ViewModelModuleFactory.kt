package com.babapanda.gitpr.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModuleFactory {

    @Binds
    internal abstract fun bindViewModelFactory(factory: GitprViewModelFactory): ViewModelProvider.Factory

}