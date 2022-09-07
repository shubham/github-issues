package com.babapanda.gitpr

import android.app.Application
import android.content.Context
import com.babapanda.gitpr.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class MainApplication : DaggerApplication() {
    //    private lateinit var appComponent: AppComponent
//    private lateinit var coreComponent: CoreComponent
    private lateinit var context: Context

    fun initDagger() {
//        coreComponent = DaggerCoreComponent
//            .builder()
//            .coreModule(CoreModule(applicationContext))
//            .build()
//
//        appComponent = DaggerAppComponent
//            .application(this)
//            .coreComponent(coreComponent)
//            .build()
//
//        appComponent.inject(this)
    }

    override fun onCreate() {
        super.onCreate()
        context = baseContext
        initDagger()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }
}