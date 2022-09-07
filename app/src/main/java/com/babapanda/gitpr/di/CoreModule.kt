package com.babapanda.gitpr.di

import android.app.Application
import android.content.Context
import com.babapanda.gitpr.util.ResourceProvider
import com.babapanda.gitpr.util.ResourceProviderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoreModule {

    @Singleton
    @Provides
    fun provideResourceProvider(application: Application): ResourceProvider {
        return ResourceProviderImpl(application.applicationContext)
    }
}