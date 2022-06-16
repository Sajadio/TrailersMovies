package com.example.trailers.di.inject

import com.example.trailers.di.annotate.ActivityScope
import com.example.trailers.di.module.ProviderViewModel
import com.example.trailers.di.module.ViewModelProvide
import com.example.trailers.ui.fragment.home.BottomSheetFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/* Install module responsible for providing ViewModel into parent component */
@Module(includes = [ViewModelProvide::class])
abstract class InjectBottomSheet {

    @ActivityScope
    @ContributesAndroidInjector(modules = [ProviderViewModel::class])
    abstract fun bind(): BottomSheetFragment

}