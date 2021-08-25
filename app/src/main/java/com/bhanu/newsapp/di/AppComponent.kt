package com.bhanu.newsapp.di

import com.bhanu.newsapp.di.module.NetworkModule
import com.bhanu.newsapp.di.module.StorageModule
import com.bhanu.newsapp.di.module.ViewModelModule
import com.bhanu.newsapp.ui.fragment.NewsDetailsFragment
import com.bhanu.newsapp.ui.fragment.NewsListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, StorageModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(fragment: NewsListFragment)
    fun inject(fragment: NewsDetailsFragment)
}