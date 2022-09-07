package com.babapanda.gitpr.di

import com.babapanda.gitpr.presentation.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(
        modules = [FragmentModule::class, ViewModelModule::class]
    )
    abstract fun contributeMainActivity(): MainActivity
}