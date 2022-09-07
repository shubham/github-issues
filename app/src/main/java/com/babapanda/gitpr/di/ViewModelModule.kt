package com.babapanda.gitpr.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.babapanda.gitpr.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}