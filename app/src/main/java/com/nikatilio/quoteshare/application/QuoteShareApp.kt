package com.nikatilio.quoteshare.application

import android.app.Application
import com.nikatilio.quoteshare.dagger.AppComponent
import com.nikatilio.quoteshare.dagger.DaggerAppComponent

class QuoteShareApp: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .applicationContext(this)
            .build()
    }
}