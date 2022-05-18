package com.example.trailers

import android.app.Application
import androidx.fragment.app.Fragment
import com.example.trailers.di.component.AppComponent
import com.example.trailers.di.component.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class App: Application(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>


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


}