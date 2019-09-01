package com.nikatilio.quoteshare.dagger

import android.content.Context
import com.nikatilio.quoteshare.ui.quotesmain.ActivityQuotesMain
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder
        fun build(): AppComponent
    }

    fun inject(activity: ActivityQuotesMain)

}