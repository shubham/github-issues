package com.babapanda.gitpr

import android.content.Context
import com.babapanda.gitpr.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class MainApplication : DaggerApplication() {

    private lateinit var context: Context

    override fun onCreate() {
        super.onCreate()
        context = baseContext
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }
}