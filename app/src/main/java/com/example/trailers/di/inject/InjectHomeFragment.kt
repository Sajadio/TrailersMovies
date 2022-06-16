package com.example.trailers.di.inject

import com.example.trailers.di.annotate.FragmentScope
import com.example.trailers.di.module.ProviderViewModel
import com.example.trailers.di.module.ViewModelProvide
import com.example.trailers.ui.fragment.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module(includes = [ViewModelProvide::class])
abstract class InjectHomeFragment {

    @FragmentScope
    @ContributesAndroidInjector(modules = [ProviderViewModel::class])
    abstract fun bind(): HomeFragment
}