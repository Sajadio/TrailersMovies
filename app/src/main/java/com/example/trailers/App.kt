package com.example.trailers

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.example.trailers.di.component.AppComponent
import com.example.trailers.di.component.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class App : Application(), HasSupportFragmentInjector, HasActivityInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var activeInjector: DispatchingAndroidInjector<Activity>


    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder()
            .application(this)
            .context(this)
            .build()
        component.inject(this)
    }

    override fun supportFragmentInjector() = fragmentInjector
    override fun activityInjector() = activeInjector
}

