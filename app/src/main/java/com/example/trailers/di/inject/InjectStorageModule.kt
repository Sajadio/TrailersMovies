package com.example.trailers.di.inject

import com.example.trailers.di.annotate.ActivityScope
import com.example.trailers.di.module.ProvideViewModel
import com.example.trailers.ui.activity.HomeActivity
import com.example.trailers.ui.fragment.home.BottomSheetFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/* Install module responsible for providing ViewModel into parent component */
@Module(includes = [ProvideViewModel::class])
abstract class InjectStorageModule {

    /* Install module into subcomponent to have access to bound fragment instance */
    @ActivityScope
    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    abstract fun bindActive(): HomeActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    abstract fun bindBottomSheet(): BottomSheetFragment

}